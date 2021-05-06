# Project4 spark source code

## Question
作业：


简答题：

以下代码：
```scala

import org.apache.spark.rdd.RDD

import org.apache.spark.{SparkConf, SparkContext, HashPartitioner}



object JoinDemo {

def main(args: Array[String]): Unit = {

val conf = new SparkConf().setAppName(this.getClass.getCanonicalName.init).setMaster("local[*]")

val sc = new SparkContext(conf)

sc.setLogLevel("WARN")



val random = scala.util.Random



val col1 = Range(1, 50).map(idx => (random.nextInt(10), s"user$idx"))

val col2 = Array((0, "BJ"), (1, "SH"), (2, "GZ"), (3, "SZ"), (4, "TJ"), (5, "CQ"), (6, "HZ"), (7, "NJ"), (8, "WH"), (0,"CD"))



val rdd1: RDD[(Int, String)] = sc.makeRDD(col1)

val rdd2: RDD[(Int, String)] = sc.makeRDD(col2)



val rdd3: RDD[(Int, (String, String))] = rdd1.join(rdd2)
  println(rdd3.dependencies)

val rdd4: RDD[(Int, (String, String))] = rdd1.partitionBy(new HashPartitioner(3)).join(rdd2.partitionBy(new HashPartitioner(3)))



println(rdd4.dependencies)

sc.stop()

}

}

```






两个打印语句的结果是什么，对应的依赖是宽依赖还是窄依赖，为什么会是这个结果；
对应的依赖：
rdd3对应的是宽依赖，rdd4对应的是窄依赖


join 操作何时是宽依赖，何时是窄依赖；

rdd3 buffer
```text
(8) MapPartitionsRDD[4] at join at JoinDemo.scala:36 []
|  MapPartitionsRDD[3] at join at JoinDemo.scala:36 []
|  CoGroupedRDD[2] at join at JoinDemo.scala:36 []
+-(8) ParallelCollectionRDD[0] at makeRDD at JoinDemo.scala:30 []
+-(8) ParallelCollectionRDD[1] at makeRDD at JoinDemo.scala:32 []
ArrayBuffer(org.apache.spark.OneToOneDependency@6ceb7b5e)

```
rdd4 buffer

```text
(3) MapPartitionsRDD[9] at join at JoinDemo.scala:40 []
|  MapPartitionsRDD[8] at join at JoinDemo.scala:40 []
|  CoGroupedRDD[7] at join at JoinDemo.scala:40 []
|  ShuffledRDD[5] at partitionBy at JoinDemo.scala:40 []
+-(8) ParallelCollectionRDD[0] at makeRDD at JoinDemo.scala:30 []
|  ShuffledRDD[6] at partitionBy at JoinDemo.scala:40 []
+-(8) ParallelCollectionRDD[1] at makeRDD at JoinDemo.scala:32 []
org.apache.spark.OneToOneDependency@27b71f50

Process finished with exit code 0
```

借助 join 相关源码，回答以上问题。
- 从源码角度解析
- 1、在join方法中使用了默认的分区器`defaultPartitioner`
```scala
 /**
   * Return an RDD containing all pairs of elements with matching keys in `this` and `other`. Each
   * pair of elements will be returned as a (k, (v1, v2)) tuple, where (k, v1) is in `this` and
   * (k, v2) is in `other`. Performs a hash join across the cluster.
   */
  def join[W](other: RDD[(K, W)]): RDD[(K, (V, W))] = self.withScope {
    join(other, defaultPartitioner(self, other))
  }
```
- 2、默认分区器，对于第一个join会返回一个以电脑core总数为分区数量的HashPartitioner.第二个join会返回我们设定的HashPartitioner(分区数目3)

```scala
def defaultPartitioner(rdd: RDD[_], others: RDD[_]*): Partitioner = {
  val rdds = (Seq(rdd) ++ others)
  val hasPartitioner = rdds.filter(_.partitioner.exists(_.numPartitions > 0))

  val hasMaxPartitioner: Option[RDD[_]] = if (hasPartitioner.nonEmpty) {
    Some(hasPartitioner.maxBy(_.partitions.length))
  } else {
    None
  }

  val defaultNumPartitions = if (rdd.context.conf.contains("spark.default.parallelism")) {
    rdd.context.defaultParallelism
  } else {
    rdds.map(_.partitions.length).max
  }

  // If the existing max partitioner is an eligible one, or its partitions number is larger
  // than the default number of partitions, use the existing partitioner.
  if (hasMaxPartitioner.nonEmpty && (isEligiblePartitioner(hasMaxPartitioner.get, rdds) ||
    defaultNumPartitions < hasMaxPartitioner.get.getNumPartitions)) {
    hasMaxPartitioner.get.partitioner.get
  } else {
    new HashPartitioner(defaultNumPartitions)
  }
}

```

- 3、走到了实际执行的join方法，里面flatMapValues是一个窄依赖，所以说如果有宽依赖应该在cogroup算子中
```scala
/**
   * Return an RDD containing all pairs of elements with matching keys in `this` and `other`. Each
   * pair of elements will be returned as a (k, (v1, v2)) tuple, where (k, v1) is in `this` and
   * (k, v2) is in `other`. Uses the given Partitioner to partition the output RDD.
   */
  def join[W](other: RDD[(K, W)], partitioner: Partitioner): RDD[(K, (V, W))] = self.withScope {
    this.cogroup(other, partitioner).flatMapValues( pair =>
      for (v <- pair._1.iterator; w <- pair._2.iterator) yield (v, w)
    )
  }
```

- 4、进入cogroup方法中，核心是CoGroupedRDD，根据两个需要join的rdd和一个分区器。由于第一个join的时候，两个rdd都没有分区器，所以在这一步，两个rdd需要先根据传入的分区器进行一次shuffle，因此第一个join是宽依赖。第二个join此时已经分好区了，不需要再再进行shuffle了。所以第二个是窄依赖
```scala

/**
   * For each key k in `this` or `other`, return a resulting RDD that contains a tuple with the
   * list of values for that key in `this` as well as `other`.
   */
  def cogroup[W](other: RDD[(K, W)], partitioner: Partitioner)
      : RDD[(K, (Iterable[V], Iterable[W]))] = self.withScope {
    if (partitioner.isInstanceOf[HashPartitioner] && keyClass.isArray) {
      throw new SparkException("HashPartitioner cannot partition array keys.")
    }
    val cg = new CoGroupedRDD[K](Seq(self, other), partitioner)
    cg.mapValues { case Array(vs, w1s) =>
      (vs.asInstanceOf[Iterable[V]], w1s.asInstanceOf[Iterable[W]])
    }
  }
```
- 5、两个都打印出OneToOneDependency，是因为在CoGroupedRDD里面，getDependencies方法里面，如果rdd有partitioner就都会返回OneToOneDependency(rdd)。
```scala
  override def getDependencies: Seq[Dependency[_]] = {
    rdds.map { rdd: RDD[_] =>
      if (rdd.partitioner == Some(part)) {
        logDebug("Adding one-to-one dependency with " + rdd)
        new OneToOneDependency(rdd)
      } else {
        logDebug("Adding shuffle dependency with " + rdd)
        new ShuffleDependency[K, Any, CoGroupCombiner](
          rdd.asInstanceOf[RDD[_ <: Product2[K, _]]], part, serializer)
      }
    }
  }****
```

join什么时候是宽依赖什么时候是窄依赖？
由上述分析可以知道，如果需要join的两个表，本身已经有分区器，且分区的数目相同，此时，相同的key在同一个分区内。就是窄依赖。反之，如果两个需要join的表中没有分区器或者分区数量不同，在join的时候需要shuffle，那么就是宽依赖

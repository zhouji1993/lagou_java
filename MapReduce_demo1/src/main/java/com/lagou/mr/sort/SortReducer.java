package com.lagou.mr.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortReducer extends Reducer<SpeakBean, NullWritable,  IntWritable, Long> {
    //reduce方法的调用是相同key的value组成一个集合调用一次
    /*
    java中如何判断两个对象是否相等？
    根据equals方法，比较还是地址值
     */
//    由题目可以知道输出结果为排名标号，和数字本身，输出为 int 和long 我这里bean中定义的类型为long

//    定义排名序号
    private int mid =0;

    @Override
    protected void reduce(SpeakBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        //讨论按照总流量排序这件事情，还需要在reduce端处理吗？因为之前已经利用mr的shuffle对数据进行了排序
        //为了避免前面compareTo方法导致总流量相等被当成对象相等，而合并了key，所以遍历values获取每个key（bean对象）


        IntWritable indexs= new IntWritable();
        LongWritable keys= new LongWritable();

        for (NullWritable value : values) {
            //遍历value同时，key也会随着遍历。
            mid++;//累加排名完成计数
            indexs.set(mid);

            context.write(indexs,key.getNumbers());

        }
    }
}

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


public class javaBatchDemon {
    public static void main(String[] args) throws Exception {
        //读取数据
        String inputPath="word.txt";
        String outputPath="wordCountOut.txt";
        //注意是批处理 前面没有Stream
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> data = env.readTextFile(inputPath);
        //处理数据 1.切分 2.拼接 3.聚合 4.累加
        FlatMapOperator<String, Tuple2<String, Integer>> flatMaped = data.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] s1 = s.split(" ");
                for (String s2 : s1) {
                    Tuple2<String, Integer> stringIntegerTuple2 = new Tuple2<>(s2, 1);
                    collector.collect(stringIntegerTuple2);
                }
            }
        });
        UnsortedGrouping<Tuple2<String, Integer>> groupByed = flatMaped.groupBy(0);
        AggregateOperator<Tuple2<String, Integer>> sum = groupByed.sum(1);
        //保存
        sum.writeAsText(outputPath);
        sum.print();
//        env.execute();
    }
}
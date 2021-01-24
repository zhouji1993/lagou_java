package com.lagou.mr.sort;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.awt.image.BandCombineOp;
import java.io.IOException;

public class SortMapper extends Mapper<LongWritable, Text, SpeakBean, NullWritable> {
    final SpeakBean bean = new SpeakBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1 读取一行文本，转为字符串，切分
        final String[] fields = value.toString().split("\t");
        //2 解析出各个字段封装成SpeakBean对象
        bean.setNumbers(Long.parseLong(fields[0]));
        //3 SpeakBean作为key输出
        context.write(bean, NullWritable.get());
    }
}

package com.lagou.mr.sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

//因为这个类的实例对象要作为map输出的key，所以要实现writablecomparalbe接口
public class SpeakBean implements WritableComparable<SpeakBean> {
    //定义属性

    private Long numbers;//总时长

    //准备构造方法


    public SpeakBean() {
    }

    public SpeakBean(Long numbers) {
        this.numbers = numbers;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

    //序列化方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(numbers);
    }

    //反序列化方法
    @Override
    public void readFields(DataInput in) throws IOException {
        this.numbers = in.readLong();

    }

    //指定排序规则,我们希望按照总时长进行排序
    @Override
    public int compareTo(SpeakBean o) {  //返回值三种：0：相等 1：小于 -1：大于
        System.out.println("compareTo 方法执行了。。。");
        //指定按照bean对象的总时长字段的值进行比较
        if (this.numbers > o.getNumbers()) {
            return -1;
        } else if (this.numbers < o.getNumbers()) {
            return 1;
        } else {
            return 0; //加入第二个判断条件，二次排序
        }
    }



    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return "SpeakBean{" +
                "numbers=" + numbers +
                '}';
    }
}

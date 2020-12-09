package com.lagou.test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.lagou.func1.Student;

public class ObjectInputStreamTest {
    public static void main(String[] args) {
        List<User> mids= new ArrayList<>();
        ObjectOutputStream oos = null;
        mids.add(new User("zhangsan","123455","15022213213"));

        try {
            // 1.创建ObjectOutputStream类型的对象与d:/a.txt文件关联
            oos = new ObjectOutputStream(new FileOutputStream("a.txt"));
            // 2.准备一个User类型的对象并初始化
            User user = new User("qidian", "123456", "13511258688");
            // 3.将整个User类型的对象写入输出流
            mids.add(user);
            oos.writeObject(mids);
            System.out.println("写入对象成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4.关闭流对象并释放有关的资源
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ObjectInputStream ois = null;

        try {
            // 1.创建ObjectInputStream类型的对象与d:/a.txt文件关联
            ois = new ObjectInputStream(new FileInputStream("a.txt"));
            // 2.从输入流中读取一个对象并打印
            Object obj = ois.readObject();
            System.out.println("读取到的对象是：" + obj); // qidian 123456 13511258688  null
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 3.关闭流对象并释放有关的资源
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}

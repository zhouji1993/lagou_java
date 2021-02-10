package com.lagou.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HbaseClientDemo {
    Configuration conf = null;
    Connection conn = null;

    @Before
    public void init() throws IOException {
        //获取一个配置文件对象
        conf = HBaseConfiguration.create();

        conf.set("hbase.zookeeper.quorum", "192.168.182.129,192.168.182.130,192.168.182.131");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        //通过conf获取到hbase集群的连接
        conn = ConnectionFactory.createConnection(conf);
    }

    @Test
    public void createTable() throws IOException {
        //获取HbaseAdmin对象用来创建表
        HBaseAdmin admin = (HBaseAdmin) conn.getAdmin();
        //创建Htabledesc描述器，表描述器
        final HTableDescriptor worker = new HTableDescriptor(TableName.valueOf("user"));
        //指定列族
        worker.addFamily(new HColumnDescriptor("friend"));
        admin.createTable(worker);
        System.out.println("user 表创建成功！！");
    }






}

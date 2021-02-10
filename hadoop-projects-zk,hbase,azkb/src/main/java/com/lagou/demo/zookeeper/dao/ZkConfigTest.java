package com.lagou.demo.zookeeper.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ZkConfigTest {


    public static void main(String[] args) throws SQLException {
        ZkConfigMag mag = new ZkConfigMag();
        config config = mag.downLoadConfigFromDB();
        System.out.println("....加载数据库配置...."+config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");

        Connection connection = mag.initDataSource(config);


        mag.upLoadConfigToDB("nm", "pw","jdbc:mysql://rm-wz9yy0528x91z1iqdco.mysql.rds.aliyuncs.com/luu_user_center?useSSL=false&useUnicode=true&characterEncoding=UTF-8","com.mysql.jdbc.Driver");
        System.out.println("....修改配置文件...."+config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");
        connection = mag.initDataSource(config);


    }

}

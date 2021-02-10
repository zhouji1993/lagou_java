package com.lagou.demo.zookeeper.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
public class ZkConfigMag {
    @Value("${config.zookeeper.url}")
    String zkUrl;   // 配置文件读取zk的地址
    @Value("${config.zookeeper.nodename}")
    String nodename;  // 配置所在的节点名

    DruidDataSource dataSource = new DruidDataSource();

    private config config;


    public config downLoadConfigFromDB() {
        //getDB
        config = new config("nm", "pw","jdbc:mysql://rm-wz9yy0528x91z1iqdco.mysql.rds.aliyuncs.com/luu_user_center?useSSL=false&useUnicode=true&characterEncoding=UTF-8","com.mysql.jdbc.Driver");
        return config;
    }

    public void upLoadConfigToDB(String nm, String pw ,String url,String driver) {
        if (config == null) config = new config();
        config.setUserNm(nm);
        config.setUserPw(pw);
        config.setDriver(driver);
        config.setUrl(url);
        //updateDB config
    }

    public Connection initDataSource (config config) throws SQLException {
        dataSource.setUrl(config.getUrl());
        dataSource.setDriverClassName(config.getDriver());
        dataSource.setUsername(config.getUserNm());
        dataSource.setPassword(config.getUserPw());

        Connection Connection = dataSource.getConnection();
        return Connection;
    }

    public void syncConfigToZk() {

        ZkClient zk = new ZkClient(zkUrl);
        if (!zk.exists(nodename)) {
            zk.createPersistent(nodename, true);
        }
        zk.writeData(nodename, config);
        zk.close();


    }


}

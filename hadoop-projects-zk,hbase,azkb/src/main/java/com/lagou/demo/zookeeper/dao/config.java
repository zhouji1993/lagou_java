package com.lagou.demo.zookeeper.dao;

import java.io.Serializable;

public class config implements Serializable {



        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private String userNm;
        private String userPw;
        private  String url;
        private  String driver;

        public config() {
        }

    public config(String userNm, String userPw, String url, String driver) {
        this.userNm = userNm;
        this.userPw = userPw;
        this.url = url;
        this.driver = driver;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "config{" +
                "userNm='" + userNm + '\'' +
                ", userPw='" + userPw + '\'' +
                ", url='" + url + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }
}

package com.lagou.beam;

import java.util.Date;

public class Student {



    private String sid ;
    private String name;
    private String sex;
    private Date brith;
    private String password;

    public Student(String sid, String name, String sex, Date brith, String password) {
        this.sid = sid;
        this.name = name;
        this.sex = sex;
        this.brith = brith;
        this.password = password;
    }

    public Student() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
            this.sex = sex;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", brith=" + brith +
                ", password='" + password + '\'' +
                '}';
    }
}

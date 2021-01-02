package com.lagou.domain;
/*
  对于employee进行定义的基类

        */





public class Dept {
    private Integer dept_id;
    private String dept_name;
    private String major_name;
    private String telepthon;
    private String email;



    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getTelepthon() {
        return telepthon;
    }

    public void setTelepthon(String telepthon) {
        this.telepthon = telepthon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dept_id=" + dept_id +
                ", dept_name='" + dept_name + '\'' +
                ", major_name='" + major_name + '\'' +
                ", telepthon='" + telepthon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

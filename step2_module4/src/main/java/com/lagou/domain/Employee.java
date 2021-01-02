package com.lagou.domain;

/*
  对于employee进行定义的基类

        */
import java.util.Date;

public class Employee {
    private Integer emp_id ;
    private String emp_name;
    private Integer dept_id;
    private String job_name;
    private Date join_date;
    private String telephone;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", dept_id=" + dept_id +
                ", job_name='" + job_name + '\'' +
                ", join_date=" + join_date +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

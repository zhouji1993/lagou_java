package com.lagou.dao;

import com.lagou.beam.Student;
import com.lagou.utils.DriudsUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentD {
    QueryRunner qr = new QueryRunner(DriudsUtil.getDataSource());


    public Student findWithId(String id ) throws SQLException {
        String sql = "select * from student where sid = ? ";
        Student stu= null;
        try {
             stu=qr.query(sql, new BeanHandler<Student>(Student.class),id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stu;
    }

    public Student checkAccount(String user, String password) throws Exception {

        String sql = "select * from student where sid = ? and password = ?";
        Student stu= null;
        try {
            stu=qr.query(sql, new BeanHandler<Student>(Student.class),user,password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return stu;
    }

    public ArrayList<Student> findWithName(String name) throws Exception{
        ArrayList<Student> al = new ArrayList<>();
        String sql = "select * from student where name = ?";
        try {
            al= (ArrayList<Student>) qr.query(sql, new  BeanListHandler<>(Student.class),name);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return al;
    }

    public int insertStudent(String id,String name, String sex, String birth, String major) throws Exception{

        String sql = "insert into student(sid, name, sex, birth, major) values(?, ?, ?, ?, ?)";
        Object[] parms={id,name,sex,birth,major};
        int state=0;
        try {
            state=qr.update(sql,parms);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }


    public boolean updateStudent(String sid, String name, String sex, String birth, String major) throws Exception{

        String sql = "update student set name =? , sex =? , birth =? , major =?) where sid =?";
        Object[] parms={name,sex,birth,major,sid};
        Boolean state=null;
        try {
            qr.update(sql,parms);
            state=true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            state=false;
        }
        return state;
    }




}

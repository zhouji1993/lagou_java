package com.lagou.func4;


import java.util.*;

public class Student {
    private String Sid;
    private String Sname;
    private String Sage;

    List<HashMap<String, String>> studentList = new ArrayList<HashMap<String , String> >();

    public List<HashMap<String, String>> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<HashMap<String, String>> studentList) {

        this.studentList = studentList;
    }



    public Student(){


    }

    public Student(String sid , String sage,String sname){
        this.Sage=sage;
        this.Sid=sid;
        this.Sname=sname;

    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        this.Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        this.Sname = sname;
    }

    public String getSage() {
        return Sage;
    }

    public void setSage(String sage) {
            this.Sage = sage;

    }

    @Override
    public int hashCode() {
        return Objects.hash(Sid, Sage, Sname);
    }

    @Override
    public String toString(){
        return "Student{"+
                "Sid='"+ Sid+'\'' +
                ",Sname='" + Sname + '\'' +
                ",age=" + Sage +
                '}';
    }

    public List<HashMap<String, String>> remove(String sid){
        for (HashMap<String, String> map:studentList){
            if (map.get("sid") == sid){
                studentList.remove(map);
            }
        }
        return  studentList;
    }

    public List<HashMap<String, String>> add(String sname , String sage ,String sid){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sname",sname);
        map.put("sid",sage);
        map.put("sage",sid);
        studentList.add(map);
        return studentList;

    }
    public List<HashMap<String, String>> update(String sname , String sage ,String sid){
        for (HashMap<String, String> map:studentList){
            if (map.get("sid") == getSid()){
                map.put("sname",sname);
                map.put("sid",sage);
                map.put("sage",sid);
                studentList.add(map);
            }
        }
        return  studentList;
    }
    public List<HashMap<String, String>> select(String sid){
        for (HashMap<String, String> map:studentList){
            if (map.get("sid") == getSid()){
                System.out.println(map);
            }
        }
        return  studentList;

    }




}

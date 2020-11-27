package com.lagou.func5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class cards {
    private String[] num = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
    private String[] color = {"梅花", "方片", "黑桃", "红心"};
    private HashMap<Integer, String> hm = new HashMap<>();
    private ArrayList<Integer> list = new ArrayList<>(); // 存储索引



    public String[] getNum() {
        return num;
    }

    public void setNum(String[] num) {
        this.num = num;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public HashMap<Integer, String> getHm() {
        return hm;
    }

    public void setHm(HashMap<Integer, String> hm) {
        int index = 0;
        for (String s1 : num) {
            for (String s2 : color) {
                hm.put(index, s2.concat(s1)); // 连接两个字符串并添加到hm中
                list.add(index);
                index++;
            }
        }
        hm.put(index, "大王");
        list.add(index);
        index++;
        hm.put(index, "小王");
        list.add(index);
        this.hm = hm;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<Integer> shuffles(){
        Collections.shuffle(list);
        return list;
    }
    // 发牌
    TreeSet<Integer> gaojin = new TreeSet<>();
    TreeSet<Integer> longwu = new TreeSet<>();
    TreeSet<Integer> me = new TreeSet<>();
    TreeSet<Integer> dipai = new TreeSet<>();

    public void shufflecard(){

        for (int i = 0; i < list.size(); i++) {
            if (i >= list.size() - 3) {                        //底牌
                dipai.add(list.get(i));
            } else if (i % 3 == 0) {                        //高进
                gaojin.add(list.get(i));
            } else if (i % 3 == 1) {                        //龙五
                longwu.add(list.get(i));
            } else if (i % 3 == 2) {                        //我
                me.add(list.get(i));
            }

        }
    }
    public static void lookpoker(HashMap<Integer, String>hm, TreeSet<Integer> ts,String name) {
        System.out.println(name+"的牌是");
        for (Integer i : ts) {
            System.out.println(hm.get(i));
        }
    }







}

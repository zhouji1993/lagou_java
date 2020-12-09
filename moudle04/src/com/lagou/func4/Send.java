package com.lagou.func4;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
    //(2)从键盘获取数据
    private BufferedReader  br;
    //发送数据的数据流
    private DataOutputStream  dos;
    private boolean flag=true;//默认为true

    public Send(){
        br=new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client){
        this();//调用本类的无参构造方法
        try {
            dos=new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            flag=false;
            CloseAll.clossSession(dos,client);
        }
    }

    private String getMessage(){
        String str="";
        try {
            str = br.readLine();
        } catch (IOException e) {
            flag=false;
            CloseAll.clossSession(br);
        }
        return str;
    }
    private void send(String str){
        try {
            dos.writeUTF(str);
            dos.flush();
        } catch (IOException e) {
            flag=false;
            CloseAll.clossSession(dos);
        }
    }
    @Override
    public void run() {
        while(flag){
            //调用发送信息的方法
            this.send(getMessage());
        }
    }

}
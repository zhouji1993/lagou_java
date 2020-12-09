package com.lagou.func4;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    //用于接收数据的数据流
    private DataInputStream dis;
    private boolean flag=true;
    public Receive(Socket client){
        try {
            dis=new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            flag=false;
            CloseAll.clossSession(dis,client);
        }

    }
    private String getMessage(){
        String str="";
        try {
            str = dis.readUTF();
        } catch (IOException e) {
            flag=false;
            CloseAll.clossSession(dis);
        }
        return str;
    }


    @Override
    public void run() {
        while(flag){
            System.out.println(this.getMessage());
        }
    }

}
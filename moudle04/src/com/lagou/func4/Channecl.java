package com.lagou.func4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Channecl implements Runnable{
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean flag =true;



    public Channecl(Socket clent) {
        try {
            dis  = new DataInputStream(clent.getInputStream());
            dos  =  new DataOutputStream(clent.getOutputStream());
        }catch (IOException e ){
            flag = false;
            CloseAll.clossSession(dis , dos);

        }



    }


    private String recive(){
        String str = "";
        try {
            str = dis.readUTF();

        }catch ( IOException e){

            flag = false;
            CloseAll.clossSession(dis);
        }
        return str;
    }

    public void send (String str) {
        if (str != null && str.length()!=0){
            try{
               dos.writeUTF(str);
                dos.flush();
            } catch (IOException e) {
                flag= false;
                CloseAll.clossSession(dos,dis);
                e.printStackTrace();
            }

        }
    }





    @Override
    public void run() {
        while(flag){}
        this.send(recive());



    }
}

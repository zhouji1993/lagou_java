package com.lagou.func4;

import java.io.Closeable;
import java.io.IOException;

public class CloseAll {

    public static void clossSession(Closeable... abe){
            for(Closeable c: abe){
                if (c != null ){
                    try {
                        c.close();
                    } catch (IOException e ){
                        e.printStackTrace();
                    }
                }
            }


    }


}

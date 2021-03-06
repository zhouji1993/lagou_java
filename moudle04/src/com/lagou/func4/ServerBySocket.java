package com.lagou.func4;

//4. 编程题
//
//        使用基于 tcp 协议的编程模型实现将 UserMessage 类型对象由客户端发送给服务器；
//
//        服 务 器接 收到 对象 后判 断 用户 对象 信息 是否 为 "admin" 和 "123456"， 若 是则 将 UserMessage 对象中的类型改为"success"，否则将类型改为"fail"并回发给客户端，客户 端接收到服务器发来的对象后判断并给出登录成功或者失败的提示。
//
//        其中 UserMessage 类的特征有：类型(字符串类型) 和 用户对象(User 类型)。
//
//        其中 User 类的特征有：用户名、密码(字符串类型)。
//
//        如：
//
//        UserMessage tum = new UserMessage("check", new User("admin", "123456"));
//
//        5. 编程题
//
//        使用基于 tcp 协议的编程模型实现多人同时在线聊天和传输文件，要求每个客户端将发 送的聊天内容和文件发送到服务器，服务器接收到后转发给当前所有在线的客户端。


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBySocket {


    public static void main(String[] args) throws IOException {


        System.out.println("服务器已经正常启动" );
        ServerSocket server  = new ServerSocket(8888);
        while(true){
            Socket socket = server.accept();
            Channecl channle= new Channecl(socket);
            FileChannel fileChannel=new FileChannel(socket);
            new Thread(channle).start();
            new Thread(fileChannel).start();
        }
    }


}

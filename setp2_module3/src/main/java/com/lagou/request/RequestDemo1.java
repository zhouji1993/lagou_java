package com.lagou.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestDemo1 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 演示request对象获取请求行信息
        System.out.println("请求方式：" + request.getMethod());
        System.out.println("虚拟路径：" + request.getContextPath());
        System.out.println("URL：" + request.getRequestURL());
        System.out.println("协议和版本：" + request.getProtocol());
        System.out.println("客户端IP地址："+ request.getRemoteAddr());




    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}

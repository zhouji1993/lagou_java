package com.lagou.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RequestDemo2 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
          // 获取请求头信息
        // 获取指定的头信息 Host
        String host = request.getHeader("Host");
        System.out.println(host);

        // 先获取到所有的请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //遍历
        while (headerNames.hasMoreElements()){
            // 请求头名称
            String name = headerNames.nextElement();
            // 根据名称获取值
            String value = request.getHeader(name);

            System.out.println(name + ":" + value);

        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

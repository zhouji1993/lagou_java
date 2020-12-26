package com.lagou.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("访问到了CSerlvet,接下来重定向到DServlet");

        // 设置重定向
      /*  resp.setStatus(302);
        resp.setHeader("Location","dServlet");*/

        // 设置重定向 方式二：常用
        resp.sendRedirect("https://www.lagou.com/");


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

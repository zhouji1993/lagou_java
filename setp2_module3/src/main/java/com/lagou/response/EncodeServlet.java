package com.lagou.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EncodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 设置编码时，一定要写在首行
        //resp.setCharacterEncoding("GBK");

        // 统一浏览器和服务器编码
        resp.setContentType("text/html;charset=utf-8");

        // 向页面输出中文
        PrintWriter writer = resp.getWriter();
        writer.write("中文..");


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

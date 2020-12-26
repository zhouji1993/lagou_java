package com.lagou.servletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CountServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {
        // 向servletContext域中存入变量count,并且初始值为0
        this.getServletContext().setAttribute("count",0);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //1. 设置响应编码
        resp.setContentType("text/html;charset=utf-8");

       //2. 向页面响应信息
        resp.getWriter().write("<h1>拉勾博客</h1>");


        //3.进行servletContext域中的取值   取  加  存    0
        // 取
        Integer count = (Integer) this.getServletContext().getAttribute("count");

        // 加
        count++;

        resp.getWriter().write("<dev>你是，第"+ count + "位访问此网站的人....</dev>");

        // 存
        this.getServletContext().setAttribute("count",count);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

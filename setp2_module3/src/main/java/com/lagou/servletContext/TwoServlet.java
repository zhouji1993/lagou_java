package com.lagou.servletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从servletContext域中取出数据
        String user = (String) req.getServletContext().getAttribute("user");

        System.out.println("TwoServlet中取出的数据为" + user);

        String encode = req.getServletContext().getInitParameter("encode");
        System.out.println("获取到的全局配置参数-"+ encode);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}

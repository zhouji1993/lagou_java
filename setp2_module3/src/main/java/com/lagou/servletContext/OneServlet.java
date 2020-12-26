package com.lagou.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 向servletContext域对象中存入数据
        //  ServletContext servletContext1 = this.getServletContext();
        ServletContext servletContext = req.getServletContext();

        servletContext.setAttribute("user","jack");
        System.out.println("OneServlet中存入了数据....");


        // servletContext获取到全局配置参数
        String encode = servletContext.getInitParameter("encode");
        System.out.println("获取到的全局配置参数-"+ encode);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

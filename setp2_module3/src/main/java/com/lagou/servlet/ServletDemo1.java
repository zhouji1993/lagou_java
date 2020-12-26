package com.lagou.servlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ServletDemo1 extends GenericServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("ServletDemo1的初始化方法执行了..");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("ServletDemo1 extends GenericServlet...");
    }


    @Override
    public void destroy() {
        super.destroy();
        System.out.println("ServletDemo1的销毁方法执行了");
    }
}

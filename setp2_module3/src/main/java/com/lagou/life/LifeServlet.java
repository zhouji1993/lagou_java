package com.lagou.life;

import javax.servlet.*;
import java.io.IOException;

public class LifeServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("LifeServlet的init方法执行了，该对象被创建完成了...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("LifeServlet的service方法执行了，执行了具体的业务逻辑...");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("LifeServlet的destroy方法执行了，该对象被销毁了...");
    }
}

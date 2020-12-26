package com.lagou.servlet;

import javax.servlet.*;
import java.io.IOException;

public class QuickServlet implements Servlet {

    /*
        init: servlet对象创建时，调用此方法完成初始化操作
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    /*
        getServletConfig：获取servletConfig配置对象
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
        对外提供服务的方法，tomcat会调用servlet里面的service方法执行具体的业务逻辑
        servletRequest：请求对象：借助该对象来获取请求参数
        servletResponse：响应对象：借助该对象来向浏览器响应一些数据
     */
    /*
        service：用户访问servlet时，调用此方法完成业务逻辑的处理
     */
    @Override
    public void service(ServletRequest servletRequest,
                        ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet入门成功了....");


    }

    /*
        获取servlet的描述信息
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /*
        destroy：当servlet对象销毁，会调用此方法完成销毁操作
     */
    @Override
    public void destroy() {

    }
}

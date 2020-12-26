package com.lagou.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("AServlet中功能上执行了");

        // 请求转发到Bservelt String path: 写的就是要跳转的资源路径
    /*    // 1.获取到转发器对象
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("https://www.baidu.com/");

        // 2.借助转发器对象进行真正的请求转发
        requestDispatcher.forward(request,resp);*/

        // 向request域中设置数据
        request.setAttribute("hanbao","香辣鸡腿堡");

        // 链式编程
        request.getRequestDispatcher("/bServlet").forward(request,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

package com.lagou.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class RequestDemo3 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 获取表单提交的请求参数
        String username = request.getParameter("username");

        System.out.println("用户名：" + username);

        // 获取爱好这样的多个value的数组类型
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println( Arrays.toString(hobbies));

        // 获取所有的请求参数的key和value  String:表单中的name属性值   String[]：请求参数的value值
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((k,v)->{
            System.out.println(k + "=" + Arrays.toString(v));
        });


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // 设置解码为UTF-8 ，解决post中文乱码问题

        doGet(request, resp); //让doGet方法中业务逻辑执行
    }
}

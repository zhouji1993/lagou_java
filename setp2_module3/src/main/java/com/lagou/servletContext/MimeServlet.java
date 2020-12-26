package com.lagou.servletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取指定文件的mime类型
        // servlet_demo/mimeServlet?fileName=a.jpg
        // 获取请求参数
        String fileName = req.getParameter("fileName");

        // 获取文件的mime类型
        String mimeType = req.getServletContext().getMimeType(fileName);
        resp.getWriter().write(fileName + "----" + mimeType);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

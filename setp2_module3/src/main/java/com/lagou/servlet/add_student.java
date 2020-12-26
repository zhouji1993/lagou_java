package com.lagou.servlet;

import com.lagou.dao.StudentD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet("/add_student")
public class add_student {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        StudentD studentD = new StudentD();
        String sid = request.getParameter("sid");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String major = request.getParameter("major");
        String school_date = request.getParameter("school_date");

        try {
            studentD.insertStudent(sid, name, sex, school_date, major);
        }
        catch (Exception e){
            out.print(e);
        }
        response.sendRedirect("one_page_student");
    }

}

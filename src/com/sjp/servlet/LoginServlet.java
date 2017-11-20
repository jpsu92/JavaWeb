package com.sjp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("userName");
        String pwd = request.getParameter("userPwd");

        if ("eric".equals(name) && "123456".equals(pwd)){
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            response.sendRedirect(request.getContextPath() + "/indexservlet");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/fail.html");
        }
    }
}

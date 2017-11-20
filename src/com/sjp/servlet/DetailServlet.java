package com.sjp.servlet;

import com.sjp.dao.ProductDao;
import com.sjp.entity.Product;
import org.omg.CORBA.Request;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao dao = new ProductDao();
        Product product = dao.findByid(id);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String html = "";

        html += "<html>";
        html += "<head>";
        html += "<title>显示商品列表</title>";
        html += "<body>";
        html += "<table border='1' align='center' width='600px'>";

        if (product != null){
            html += "<tr>";
            html += "<th>商品编号:</th><td>"+product.getId()+"</td>";
            html += "<th>商品名称:</th><td>"+product.getName()+"</td>";
            html += "<th>商品类型:</th><td>"+product.getType()+"</td>";
            html += "<th>商品价格:</th><td>"+product.getPrice()+"</td>";
            html += "</tr>";
        }
        html += "</table>";
        html += "<center><a href='"+ request.getContextPath()+"/listservlet'>返回列表</a></center>";
        html += "</body>";
        html += "</head>";
        html += "</html>";

        writer.write(html);

        /*Cookie cookie = new Cookie("historyProducts", createCookieValue());
        cookie.setMaxAge(1*30*24*60*60);
        response.addCookie(cookie);*/

    }

    /*private String createCookieValue(HttpServletRequest request) {
        Cookie[] preCookie = request.getCookies();
        if(preCookie != null){

        }
    }*/
}

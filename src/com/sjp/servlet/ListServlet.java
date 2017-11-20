package com.sjp.servlet;

import com.sjp.dao.ProductDao;
import com.sjp.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        List<Product> products = dao.findAll();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String html = "";

        html += "<html>";
        html += "<head>";
        html += "<title>显示商品列表</title>";
        html += "<body>";
        html += "<table border='1' align='center' width='600px'>";
        html += "<tr>";
        html += "<th>商品编号</th><th>商品名称</th><th>商品类型</th><th>商品价格</th>";
        html += "</tr>";

        for (Product product : products){
            html += "<tr>";
            html += "<td>"+ product.getId() + "</td><td><a href= '"+ request.getContextPath()+"detailservlet?id="+product.getId()+"'>" + product.getName() + "</a></td><td>"+product.getType()+"</td><td>"+product.getPrice()+"</td>";
            html += "</tr>";
        }

        html += "</table>";

        html += "最近浏览过的商品<br/>";
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for(Cookie item : cookies){
                if (item.getName().equals("historyProducts")){
                    String value = item.getValue();
                    String[] ids = value.split(",");
                    for (String id : ids){
                        Product product = dao.findByid(id);
                        if (product != null){
                            html += "<tr>";
                            html += "<th>商品编号:</th><td>"+product.getId()+"</td>";
                            html += "<th>商品名称:</th><td>"+product.getName()+"</td>";
                            html += "<th>商品类型:</th><td>"+product.getType()+"</td>";
                            html += "<th>商品价格:</th><td>"+product.getPrice()+"</td>";
                            html += "</tr>";
                        }
                    }
                }
            }
        }

        html += "</body>";
        html += "</head>";
        html += "</html>";

        writer.write(html);
    }
}

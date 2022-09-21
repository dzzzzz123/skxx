package org.example.controller;

import org.example.entity.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
/**继承HttpServlet*/
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        System.out.println("------>请求WEB-INF下的"+page);
        //转发
        request.getRequestDispatcher("/WEB-INF/pages/"+page).forward(request,response);
    }
}

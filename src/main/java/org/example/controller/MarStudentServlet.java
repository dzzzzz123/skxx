package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dz
 * @date 2022-09-21
 */
@WebServlet("/mStudent/*")
public class MarStudentServlet extends BaseServlet {

    private MarStudentService marStudentService = new MarStudentServiceImpl();

    protected void marStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MarStudentServlet--------->marStudentList");

    }
}

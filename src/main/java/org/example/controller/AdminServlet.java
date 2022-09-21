package org.example.controller;


import com.alibaba.fastjson.JSON;
import org.example.entity.Admin;
import org.example.service.AdminService;
import org.example.service.impl.AdminServiceImpl;
import org.example.util.JsonUtil;
import org.example.util.ResultModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author dz
 */
@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {

    private AdminService adminService = new AdminServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AdminServlet--------->login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResultModel resultModel = adminService.login(username, password);

        if (resultModel.getSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", resultModel.getData());
        }

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AdminServlet--------->logout");
        HttpSession session = request.getSession();
        session.removeAttribute("admin");
        session.invalidate();
        session = null;
        JsonUtil.sendJsonStr(response, JSON.toJSONString(ResultModel.success("安全退出成功!")));
    }

}

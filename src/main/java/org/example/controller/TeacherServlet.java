package org.example.controller;


import com.alibaba.fastjson.JSON;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.service.TeacherService;
import org.example.service.impl.TeacherServiceImpl;
import org.example.util.DateUtil;
import org.example.util.JsonUtil;
import org.example.util.ResultModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author dz
 */
@WebServlet("/teacher/*")
public class TeacherServlet extends BaseServlet {

    private TeacherService teacherService = new TeacherServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResultModel resultModel = teacherService.login(username, password);

        if (resultModel.getSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute("teacher", resultModel.getData());
        }

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->logout");
        HttpSession session = request.getSession();
        session.removeAttribute("teacher");
        session.invalidate();
        session = null;
        JsonUtil.sendJsonStr(response, JSON.toJSONString(ResultModel.success("安全退出成功!")));
    }

    protected void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->updateTeacher");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String bankAccount = request.getParameter("bankAccount");
        String salary = request.getParameter("salary");
        String info = request.getParameter("info");
        String img = request.getParameter("img");
        Teacher teacher = new Teacher(null, account, pwd, name, birth, info, sex, img, phone, bankAccount, salary, title);

        ResultModel resultModel = teacherService.updateByAccount(teacher);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void selectTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->selectTeacher");
        String teacherId = request.getParameter("teacherId");

        ResultModel resultModel = teacherService.selectOne(teacherId);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void teacherList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->teacherList");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        System.out.printf("page=%s, limit=%s ", page, limit);

        ResultModel resultModel = teacherService.teacherList(page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->deleteTeacher");
        String id = request.getParameter("id");
        System.out.println("你要删除的教师id为" + id);

        ResultModel resultModel = teacherService.deleteTeacher(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->addTeacher");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String bankAccount = request.getParameter("bankAccount");
        String salary = request.getParameter("salary");
        String info = request.getParameter("info");
        String img = request.getParameter("img");
        Teacher teacher = new Teacher(null, account, pwd, name, birth, info, sex, img, phone, bankAccount, salary, title);

        ResultModel resultModel = teacherService.add(teacher);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void resetTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->resetTeacher");
        String id = request.getParameter("id");
        ResultModel resultModel = teacherService.resetTeacher(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void getImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->getImg");
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        String img = teacher.getTImg();
        String jsonStr = JSON.toJSONString(img);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void commentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TeacherServlet--------->commentList");
        String cid = request.getParameter("cid");
        Integer tid = ((Teacher) request.getSession().getAttribute("teacher")).getTId();

        ResultModel resultModel = teacherService.selectCommentList(cid,tid);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

}

package org.example.controller;


import com.alibaba.fastjson.JSON;
import org.example.entity.Comment;
import org.example.entity.Student;
import org.example.service.CourseService;
import org.example.service.StudentService;
import org.example.service.impl.CourseServiceImpl;
import org.example.service.impl.StudentServiceImpl;
import org.example.util.JsonUtil;
import org.example.util.ResultModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author dz
 */
@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {

    private StudentService studentService = new StudentServiceImpl();

    private CourseService courseService = new CourseServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResultModel resultModel = studentService.login(username, password);

        if (resultModel.getSuccess()) {
            HttpSession session = request.getSession();
            session.setAttribute("student", resultModel.getData());
        }

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->logout");
        HttpSession session = request.getSession();
        session.removeAttribute("student");
        session.invalidate();
        session = null;
        JsonUtil.sendJsonStr(response, JSON.toJSONString(ResultModel.success("安全退出成功!")));
    }

    protected void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->updateStudent");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String info = request.getParameter("info");
        String img = request.getParameter("img");
        Student student = new Student(null, account, pwd, name, birth, info, age, sex, img, null);

        ResultModel resultModel = studentService.updateByAccount(student);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void selectStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->selectStudent");
        String studentId = request.getParameter("studentId");

        ResultModel resultModel = studentService.selectOne(studentId);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void studentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->studentList");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        ResultModel resultModel = studentService.studentList(page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->deleteStudent");
        String id = request.getParameter("id");

        ResultModel resultModel = studentService.deleteStudent(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->addStudent");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String info = request.getParameter("info");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        String img = request.getParameter("img");
        Student student = new Student(null, account, pwd, name, birth, info, age, sex, img, null);
        ResultModel resultModel = studentService.addStudent(student);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void resetStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->resetStudent");
        String id = request.getParameter("id");

        ResultModel resultModel = studentService.resetStudent(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void toMarStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->toMarStudent");
        String id = request.getParameter("id");
        ResultModel resultModel = studentService.toMarStudent(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void myCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->myCourseList");
        Integer id = ((Student) request.getSession().getAttribute("student")).getSId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        ResultModel resultModel = courseService.myCourseList(id, page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void selectCourseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->myCourseList");

        Integer id = ((Student) request.getSession().getAttribute("student")).getSId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        ResultModel resultModel = courseService.selectCourseList(id, page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void getImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->getImg");
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        String img = student.getSImg();
        String jsonStr = JSON.toJSONString(img);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void selectStudentByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->selectStudentByCid");
        String cid = request.getParameter("cid");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        ResultModel resultModel = courseService.selectStudentByCid(Integer.parseInt(cid), page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void studentScore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->studentScore");
        Integer id = ((Student) request.getSession().getAttribute("student")).getSId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        ResultModel resultModel = courseService.selectStudentScore(id, page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void getComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet--------->getComment");
        Integer sid = ((Student) request.getSession().getAttribute("student")).getSId();
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        ResultModel resultModel = courseService.selectComment(sid, cid);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void editComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer sid = ((Student) request.getSession().getAttribute("student")).getSId();
        String comment = request.getParameter("comment");
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        Integer star = Integer.valueOf(request.getParameter("star"));
        Comment commentObj = new Comment(cid, sid, comment, star);
        ResultModel resultModel = courseService.editComment(commentObj);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

}

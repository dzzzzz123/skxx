package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.example.entity.MarStudent;
import org.example.entity.Student;
import org.example.service.MarStudentService;
import org.example.service.StudentService;
import org.example.service.impl.MarStudentServiceImpl;
import org.example.service.impl.StudentServiceImpl;
import org.example.util.JsonUtil;
import org.example.util.ResultModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author dz
 * @date 2022-09-21
 */
@WebServlet("/mStudent/*")
public class MarStudentServlet extends BaseServlet {

    private MarStudentService marStudentService = new MarStudentServiceImpl();
    private StudentService studentService = new StudentServiceImpl();


    protected void marStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MarStudentServlet--------->marStudentList");

        ResultModel resultModel = marStudentService.marStudentList();
        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);

    }

    protected void addMarStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MarStudentServlet--------->addMarStudent");
        String id = request.getParameter("id");
        Student student = (Student) studentService.selectOne(id).getData();
        String contribution = request.getParameter("contribution");
        String achievement = request.getParameter("achievement");
        MarStudent marStudent = new MarStudent(student.getSId(), student.getSImg(), student.getSName(), student.getSInfo(), contribution, achievement);

        ResultModel resultModel = marStudentService.addMarStudent(marStudent);
        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void studentToMar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MarStudentServlet--------->studentToMar");
        List<Student> studentList = (List<Student>) studentService.selectMarStudent().getData();
        if (studentList.size() > 0) {
            for (Student student : studentList) {
                System.out.println("student = " + student);
                MarStudent marStudent = new MarStudent(student.getSId(), student.getSName(), student.getSInfo(), student.getSImg(), null, null);
                System.out.println("marStudent = " + marStudent);
                marStudentService.initMarStudent(marStudent);
            }
        }
    }
}

package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.service.CourseService;
import org.example.service.impl.CourseServiceImpl;
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
@WebServlet("/course/*")
public class CourseServlet extends BaseServlet {
    private CourseService courseService = new CourseServiceImpl();

    protected void courseList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet--------->courseList");
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        System.out.printf("page=%s, limit=%s 老师id为=%s", page, limit, id);

        ResultModel resultModel = courseService.courseList(id, page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet--------->courseList");
        String id = request.getParameter("id");

        ResultModel resultModel = courseService.deleteCourse(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet--------->addCourse");
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String credit = request.getParameter("credit");
        String require = request.getParameter("require");
        Integer tId = teacher.getTId();
        Course course = new Course(null, name, startDate, endDate, Integer.parseInt(credit), require, tId);

        ResultModel resultModel = courseService.addCourse(course);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void editCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet--------->editCourse");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String credit = request.getParameter("credit");
        String require = request.getParameter("require");
        Course course = new Course(Integer.parseInt(id), name, startDate, endDate, Integer.parseInt(credit), require, null);

        ResultModel resultModel = courseService.editCourse(course);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void addCourseStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet--------->addCourseStudent");
        String cidArr = request.getParameter("cidArr");
        Integer id = ((Student) request.getSession().getAttribute("student")).getSId();
        List<Integer> list = JSON.parseArray(cidArr, Integer.class);
        ResultModel resultModel = courseService.addCourseStudent(list, id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }
}

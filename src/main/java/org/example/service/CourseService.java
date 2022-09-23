package org.example.service;

import org.example.entity.Course;
import org.example.util.ResultModel;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-21
 */
public interface CourseService {

    /**
     * 使用分页对老师所授课程进行查询
     * @param tid
     * @param page
     * @param limit
     * @return
     */
    ResultModel courseList(Integer tid, String page, String limit);

    /**
     * 根据课程id删除课程
     * @param id
     * @return
     */
    ResultModel deleteCourse(String id);

    /**
     * 老师添加课程
     * @param course
     * @return
     */
    ResultModel addCourse(Course course);

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    ResultModel editCourse(Course course);

    /**
     * 使用分页查询根据学生id查询出所有课程
     * @param id
     * @param page
     * @param limit
     * @return
     */
    ResultModel myCourseList(String id, String page, String limit);

    /**
     * 使用分页查询根据学生id查询学生所有没有选择的课程
     * @param id
     * @param page
     * @param limit
     * @return
     */
    ResultModel selectCourseList(Integer id, String page, String limit);

    /**
     * 学生选择课程，并插入选课表中
     * @param list
     * @param id
     * @return
     */
    ResultModel addCourseStudent(List<Integer> list, Integer id);

    /**
     * 查询课程下所有学生信息
     * @param cid
     * @param page
     * @param limit
     * @return
     */
    ResultModel selectStudentByCid(Integer cid, String page, String limit);

    /**
     * 使用分页根据学生id查询学生的所有课程成绩
     * @param id
     * @param page
     * @param limit
     * @return
     */
    ResultModel selectStudentScore(Integer id,String page,String limit);
}

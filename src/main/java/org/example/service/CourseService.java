package org.example.service;

import org.example.entity.Course;
import org.example.util.ResultModel;

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
    ResultModel courseList(String tid, String page, String limit);

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
}

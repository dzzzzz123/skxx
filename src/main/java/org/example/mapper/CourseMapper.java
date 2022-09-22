package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Course;
import org.example.vo.CourseStudentVO;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-21
 */
public interface CourseMapper {

    /**
     * 根据老师id查询所有学生
     *
     * @param tId
     * @return
     */
    List<Course> queryAll(@Param("tId") int tId);

    /**
     * 根据id删除课程
     * @param cId
     * @return
     */
    int deleteCourse(@Param("cId") int cId);

    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    int editCourse(Course course);


    /**
     * 根据学生id来查询该学生的所有课程信息
     * @param sId
     * @return
     */
    List<CourseStudentVO> getCourseBysId(@Param("sId") Integer sId);
}

package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.mapper.CourseMapper;
import org.example.mapper.TeacherMapper;
import org.example.service.CourseService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-21
 */
public class CourseServiceImpl implements CourseService {

    @Override
    public ResultModel courseList(String tid, String page, String limit) {
        ResultModel resultModel = ResultModel.fail("查询失败！");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession(false);
            CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
            List<Course> courseList = courseMapper.queryAll(Integer.parseInt(tid));
            PageInfo<Course> pageInfo = new PageInfo<>(courseList);
            int total = (int) pageInfo.getTotal();
            resultModel = ResultModel.success(total, courseList);
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteCourse(String id) {
        ResultModel resultModel = ResultModel.fail("删除失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        int n = courseMapper.deleteCourse(Integer.parseInt(id));
        if (n > 0) {
            resultModel = ResultModel.success("删除成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel addCourse(Course course) {
        ResultModel resultModel = ResultModel.fail("删除失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        int n = courseMapper.addCourse(course);
        if (n > 0) {
            resultModel = ResultModel.success("删除成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel editCourse(Course course) {
        ResultModel resultModel = ResultModel.fail("删除失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        int n = courseMapper.editCourse(course);
        if (n > 0) {
            resultModel = ResultModel.success("删除成功！");
        }
        sqlSession.close();
        return resultModel;
    }
}

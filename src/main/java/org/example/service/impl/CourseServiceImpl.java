package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.mapper.CourseMapper;
import org.example.service.CourseService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;
import org.example.vo.CourseStudentVO;
import org.example.vo.StudentScoreVO;

import java.util.List;
import java.util.Objects;

/**
 * @author dz
 * @date 2022-09-21
 */
public class CourseServiceImpl implements CourseService {

    @Override
    public ResultModel courseList(Integer tid, String page, String limit) {
        ResultModel resultModel = ResultModel.fail("查询失败！");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession(false);
            CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
            List<Course> courseList = courseMapper.queryAll(tid);
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
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
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
        ResultModel resultModel = ResultModel.fail("添加课程失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        int n = courseMapper.addCourse(course);
        if (n > 0) {
            resultModel = ResultModel.success("添加课程成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel editCourse(Course course) {
        ResultModel resultModel = ResultModel.fail("修改课程失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        int n = courseMapper.editCourse(course);
        if (n > 0) {
            resultModel = ResultModel.success("修改课程成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel myCourseList(String id, String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        List<CourseStudentVO> list = courseMapper.getCourseBysId(Integer.valueOf(id));
        PageInfo<CourseStudentVO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        sqlSession.close();
        return ResultModel.success(total, list);
    }

    @Override
    public ResultModel selectCourseList(Integer id, String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        List<CourseStudentVO> list = courseMapper.getCourseBysId2(id);
        PageInfo<CourseStudentVO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        sqlSession.close();
        return ResultModel.success(total, list);
    }

    @Override
    public ResultModel addCourseStudent(List<Integer> list, Integer id) {
        ResultModel resultModel = ResultModel.fail("学生选课失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        if (list.size() > 0 && Objects.nonNull(id)) {
            int n = courseMapper.addCourseStudent(list, id);
            if (n > 0) {
                resultModel = ResultModel.success("学生选课成功！");
            }
            sqlSession.close();
        }
        return resultModel;
    }

    @Override
    public ResultModel selectStudentByCid(Integer cid, String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        List<Student> studentList = courseMapper.selectStudentByCid(cid);
        PageInfo<Student> pageInfo = new PageInfo<>(studentList);
        long total = pageInfo.getTotal();
        sqlSession.close();
        return ResultModel.success(total, studentList);
    }

    @Override
    public ResultModel selectStudentScore(Integer id,String page, String limit) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        CourseMapper courseMapper = MybatisUtil.getMapper(CourseMapper.class, sqlSession);
        List<StudentScoreVO> scoreList = courseMapper.selectStudentScore(id);
        PageInfo<StudentScoreVO> pageInfo = new PageInfo<>(scoreList);
        long total = pageInfo.getTotal();
        sqlSession.close();
        return ResultModel.success(total, scoreList);
    }
}

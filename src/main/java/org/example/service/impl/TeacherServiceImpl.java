package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.example.entity.News;
import org.example.entity.Teacher;
import org.example.mapper.NewsMapper;
import org.example.mapper.TeacherMapper;
import org.example.service.TeacherService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;
import org.example.vo.CommentVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author dz
 * @date 2022-09-15
 */
public class TeacherServiceImpl implements TeacherService {

    @Override
    public ResultModel login(String tAccount, String tPwd) {
        ResultModel resultModel = ResultModel.fail("登录失败,密码错误!");
        // 调用mapper接口的方法
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        Teacher teacher = teacherMapper.selectTeacher(tAccount, tPwd);
        if (Objects.nonNull(teacher)) {
            return ResultModel.success("登录成功!", teacher);
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel selectOne(String teacherId) {
        ResultModel resultModel = ResultModel.fail("查询信息失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        Teacher teacher = teacherMapper.selectOne(Integer.valueOf(teacherId));
        if (Objects.nonNull(teacher)) {
            return ResultModel.success("登录成功!", teacher);
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel updateByAccount(Teacher teacher) {
        ResultModel resultModel = ResultModel.fail("修改信息失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        int n = teacherMapper.update(teacher);
        if (n > 0) {
            return ResultModel.success("修改信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel teacherList(String page, String limit) {
        ResultModel resultModel = ResultModel.fail("查询失败！");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession(false);
            TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
            List<Teacher> teachersList = teacherMapper.queryAll();
            PageInfo<Teacher> pageInfo = new PageInfo<>(teachersList);
            int total = (int) pageInfo.getTotal();
            resultModel = ResultModel.success(total, teachersList);
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteTeacher(String id) {
        ResultModel resultModel = ResultModel.fail("删除老师失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        int n = teacherMapper.delete(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("删除老师信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel add(Teacher teacher) {
        ResultModel resultModel = ResultModel.fail("修改信息失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        int n = teacherMapper.add(teacher);
        if (n > 0) {
            resultModel = ResultModel.success("修改信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel resetTeacher(String id) {
        ResultModel resultModel = ResultModel.fail("重置失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        int n = teacherMapper.reset(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("重置成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel selectCommentList(String cid, Integer tid) {
        ResultModel resultModel = ResultModel.fail("此课程下无任何学生评论！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        TeacherMapper teacherMapper = MybatisUtil.getMapper(TeacherMapper.class, sqlSession);
        List<CommentVO> commentVOList;
        if (Integer.parseInt(cid) != 0) {
            commentVOList = teacherMapper.getCommentByCid(Integer.valueOf(cid));
        } else {
            commentVOList = teacherMapper.getAllComments(tid);
        }
        if (commentVOList.size() > 0) {
            resultModel = ResultModel.success("查询成功！", commentVOList);
        }
        return resultModel;
    }
}

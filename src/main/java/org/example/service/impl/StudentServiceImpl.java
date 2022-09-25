package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.xdevapi.Table;
import org.apache.ibatis.session.SqlSession;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.mapper.StudentMapper;
import org.example.mapper.TeacherMapper;
import org.example.service.StudentService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;

import java.util.List;
import java.util.Objects;

/**
 * @author dz
 * @date 2022-09-15
 */
public class StudentServiceImpl implements StudentService {

    @Override
    public ResultModel login(String sAccount, String sPwd) {
        ResultModel resultModel = ResultModel.fail("登录失败,密码错误!");
        // 调用mapper接口的方法
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        Student student = studentMapper.selectStudent(sAccount, sPwd);
        if (Objects.nonNull(student)) {
            return ResultModel.success("登录成功!", student);
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel updateByAccount(Student student) {
        ResultModel resultModel = ResultModel.fail("修改信息失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        int n = studentMapper.update(student);
        if (n > 0) {
            return ResultModel.success("修改信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel selectOne(String studentId) {
        ResultModel resultModel = ResultModel.fail("查询信息失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(false);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        Student student = studentMapper.selectOne(Integer.valueOf(studentId));
        if (Objects.nonNull(student)) {
            return ResultModel.success("登录成功!", student);
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel studentList(String page, String limit) {
        ResultModel resultModel = ResultModel.fail("查询信息失败!");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession(false);
            StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
            List<Student> studentList = studentMapper.queryAll();
            PageInfo<Student> pageInfo = new PageInfo<>(studentList);
            int total = (int) pageInfo.getTotal();
            resultModel = ResultModel.success(total, studentList);
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    @Override
    public ResultModel deleteStudent(String id) {
        ResultModel resultModel = ResultModel.fail("删除学生失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);

        int n = studentMapper.delete(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("删除学生成功！");
        }
        sqlSession.close();
        return resultModel;

    }

    @Override
    public ResultModel addStudent(Student student) {
        ResultModel resultModel = ResultModel.fail("添加学生失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);

        int n = studentMapper.add(student);
        if (n > 0) {
            resultModel = ResultModel.success("添加学生成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel resetStudent(String id) {
        ResultModel resultModel = ResultModel.fail("重置学生失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        int n = studentMapper.reset(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("重置学生成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel selectMarStudent() {
        ResultModel resultModel = ResultModel.fail("查询优秀学生失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        List<Student> studentList = studentMapper.selectMarStudent();
        if (Objects.nonNull(studentList)) {
            resultModel = ResultModel.success("查询优秀学生成功！",studentList);
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel toMarStudent(String id) {
        ResultModel resultModel = ResultModel.fail("设置学生为优秀校友失败!");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class, sqlSession);
        int n = studentMapper.toMar(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("设置学生为优秀校友成功！");
        }
        sqlSession.close();
        return resultModel;
    }
}

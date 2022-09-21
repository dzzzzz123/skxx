package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.entity.MarStudent;
import org.example.mapper.MarStudentMapper;
import org.example.service.MarStudentService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;

import java.util.List;
import java.util.Objects;

/**
 * @author dz
 * @date 2022-09-21
 */
public class MarStudentServiceImpl implements MarStudentService {
    @Override
    public ResultModel addMarStudent(MarStudent marStudent) {
        ResultModel resultModel = ResultModel.fail("添加优秀校友失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        MarStudentMapper marStudentMapper = sqlSession.getMapper(MarStudentMapper.class);
        int n = marStudentMapper.add(marStudent);
        if (n > 0) {
            resultModel = ResultModel.success("添加优秀校友成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public void initMarStudent(MarStudent marStudent) {
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        MarStudentMapper marStudentMapper = sqlSession.getMapper(MarStudentMapper.class);
        int n = marStudentMapper.init(marStudent);
        sqlSession.close();
    }

    @Override
    public ResultModel marStudentList() {
        ResultModel resultModel = ResultModel.fail("查询优秀校友失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        MarStudentMapper marStudentMapper = sqlSession.getMapper(MarStudentMapper.class);
        List<MarStudent> marStudentList = marStudentMapper.queryAll();
        if (Objects.nonNull(marStudentList)) {
            resultModel = ResultModel.success("查询优秀校友成功！", marStudentList);
        }
        sqlSession.close();
        return resultModel;
    }
}

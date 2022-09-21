package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.entity.Admin;
import org.example.mapper.AdminMapper;
import org.example.service.AdminService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;

import java.util.Objects;

/**
 * @author dz
 * @date 2022-09-15
 */
public class AdminServiceImpl implements AdminService {

    @Override
    public ResultModel login(String aAccount, String aPwd) {
        ResultModel resultModel=ResultModel.fail("登录失败,密码错误!");
        //调用mapper接口的方法
        SqlSession sqlSession= MybatisUtil.getSqlSession(false);
        AdminMapper adminMapper=MybatisUtil.getMapper(AdminMapper.class,sqlSession);
        Admin admin = adminMapper.selectAdmin(aAccount, aPwd);
        if(Objects.nonNull(admin)){
            return ResultModel.success("登录成功!",admin);
        }
        sqlSession.close();
        return resultModel;
    }
}

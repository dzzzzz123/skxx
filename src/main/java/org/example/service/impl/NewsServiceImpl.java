package org.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.example.entity.News;
import org.example.mapper.NewsMapper;
import org.example.service.NewsService;
import org.example.util.MybatisUtil;
import org.example.util.ResultModel;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-19
 */
public class NewsServiceImpl implements NewsService {

    @Override
    public ResultModel newsList(String page, String limit) {
        ResultModel resultModel = ResultModel.fail("查询失败！");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        try {
            SqlSession sqlSession = MybatisUtil.getSqlSession(true);
            NewsMapper newsMapper = MybatisUtil.getMapper(NewsMapper.class, sqlSession);
            List<News> newsList = newsMapper.queryAll();
            PageInfo<News> pageInfo = new PageInfo<>(newsList);
            int total = (int) pageInfo.getTotal();
            resultModel = ResultModel.success(total, newsList);
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    @Override
    public ResultModel delete(String id) {
        ResultModel resultModel = ResultModel.fail("删除新闻失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        NewsMapper newsMapper = MybatisUtil.getMapper(NewsMapper.class, sqlSession);
        int n = newsMapper.deleteById(Integer.valueOf(id));
        if (n > 0) {
            resultModel = ResultModel.success("删除新闻成功！");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel update(News news) {
        ResultModel resultModel = ResultModel.fail("修改新闻信息失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        NewsMapper newsMapper = MybatisUtil.getMapper(NewsMapper.class, sqlSession);
        int n = newsMapper.updateById(news);
        if (n > 0) {
            resultModel = ResultModel.success("修改新闻信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }

    @Override
    public ResultModel add(News news) {
        ResultModel resultModel = ResultModel.fail("添加新闻信息失败！");
        SqlSession sqlSession = MybatisUtil.getSqlSession(true);
        NewsMapper newsMapper = MybatisUtil.getMapper(NewsMapper.class, sqlSession);
        int n = newsMapper.add(news);
        if (n > 0) {
            resultModel = ResultModel.success("添加新闻信息成功!");
        }
        sqlSession.close();
        return resultModel;
    }
}

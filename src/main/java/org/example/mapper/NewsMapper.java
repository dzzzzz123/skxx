package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.entity.News;

import java.util.List;

/**
 * @author dz
 * @date 2022-09-19
 */
public interface NewsMapper {
    /**
     * 查询所有新闻
     * @return
     */
    List<News> queryAll();

    /**
     * 根据id删除新闻
     * @param nId
     * @return
     */
    int deleteById(@Param("nId") Integer nId);

    /**
     * 根据新闻id修改新闻信息
     * @param news
     * @return
     */
    int updateById(News news);

    /**
     * 添加新闻信息
     * @param news
     * @return
     */
    int add(News news);
}

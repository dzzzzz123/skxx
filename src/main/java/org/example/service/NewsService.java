package org.example.service;

import org.example.entity.News;
import org.example.util.ResultModel;

/**
 * @author dz
 * @date 2022-09-19
 */
public interface NewsService {
    /**
     * 分页查询所有新闻信息
     * @param page
     * @param limit
     * @return
     */
    ResultModel newsList(String page,String limit);

    /**
     * 更具id删除新闻
     * @param id
     * @return
     */
    ResultModel delete(String id);

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    ResultModel update(News news);

    /**
     * 添加新闻
     * @param news
     * @return
     */
    ResultModel add(News news);
}

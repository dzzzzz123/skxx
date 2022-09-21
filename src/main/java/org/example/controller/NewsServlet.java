package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.example.entity.News;
import org.example.service.NewsService;
import org.example.service.impl.NewsServiceImpl;
import org.example.util.JsonUtil;
import org.example.util.ResultModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author dz
 */
@WebServlet("/news/*")
public class NewsServlet extends BaseServlet {

    private NewsService newsService = new NewsServiceImpl();

    protected void newsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("NewsServlet--------->newsList");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        System.out.printf("page=%s, limit=%s ", page, limit);

        ResultModel resultModel = newsService.newsList(page, limit);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void deleteNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("NewsServlet--------->deleteNews");
        String id = request.getParameter("id");
        System.out.println("您删除的id为" + id);
        ResultModel resultModel = newsService.delete(id);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void updateNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("NewsServlet--------->updateNews");
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String img = request.getParameter("img");
        String content = request.getParameter("content");
        News news = new News(Integer.valueOf(id), img, title, content);
        ResultModel resultModel = newsService.update(news);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void addNews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("NewsServlet--------->addNews");

        String title = request.getParameter("title");
        String img = request.getParameter("img");
        String content = request.getParameter("content");
        News news = new News(null, img, title, content);
        ResultModel resultModel = newsService.add(news);

        String jsonStr = JSON.toJSONString(resultModel);
        JsonUtil.sendJsonStr(response, jsonStr);
    }
}

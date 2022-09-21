package org.example.controller;

import com.alibaba.fastjson.JSON;
import org.example.util.JsonUtil;
import org.example.util.QiniuOssUtil;
import org.example.util.ResultModel;
import org.example.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * @author dz
 */
@WebServlet("/UploadServlet/*")
@MultipartConfig
public class UploadServlet extends BaseServlet {


    protected void uploadXqImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("请求UploadServlet中的uploadXqImg方法(上传小区图片)");
        // 获取页面传输过来的附件
        Part part = request.getPart("file");
        // 将附件存入到磁盘目录中
        String imgName = UploadUtil.uploadOldNameFile(part, "D:/imgs/skxx");
        System.out.println("imgName = " + imgName);
        // 将数据打包成ResultModel对象
        ResultModel resultModel = ResultModel.success("图片上传成功！", imgName);
        // 将对象变成json格式
        String jsonStr = JSON.toJSONString(resultModel);
        // 将字符串传递给前端页面
        JsonUtil.sendJsonStr(response, jsonStr);
    }

    protected void uploadToQiNiu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultModel resultModel = ResultModel.fail("文件上传失败!");
        Collection<Part> parts = request.getParts();
        if (Objects.nonNull(parts)) {
            for (Part part : parts) {
                if (part.getSize() > 0) {
                    String fileName = UploadUtil.uploadOldNameFile(part, "D:/imgs/skxx");
                    // 你的磁盘路径
                    String realPath = "D:/imgs/skxx/" + fileName;
                    String returnPath = QiniuOssUtil.upload(realPath);
                    if (fileName != null) {
                        // 七牛云服务器返回给我们的网络上的图片
                        resultModel = ResultModel.success("图片上传成功", returnPath);
                        // 把本地磁盘上的文件删除
                        File distFile = new File(realPath);
                        distFile.delete();
                    }
                }
            }
        }
        System.out.println("测试输出文件上传的结果:" + resultModel);
        JsonUtil.sendJsonStr(response, JSON.toJSONString(resultModel));
    }
}

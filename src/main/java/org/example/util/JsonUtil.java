package org.example.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author qiufen
 * @date 2022-09-05
 */
public class JsonUtil {


    /**
     * 将json格式的字符串，传递给前端页面
     * @param response
     * @param jsonStr
     */
    public static void sendJsonStr(HttpServletResponse response, String jsonStr) {
        try {
            //1.解决乱码问题
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            //2.获取字符输出流
            PrintWriter pw = response.getWriter();
            //3.传输数据
            pw.println(jsonStr);
            //4.刷新流
            pw.flush();
            //5.关闭流
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

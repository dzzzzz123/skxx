package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.endsWith(".do")) {
            String methodName = uri.substring(uri.lastIndexOf("/") + 1);
            methodName = methodName.substring(0, methodName.lastIndexOf(".do"));
            String className = null;
            try {
                Class<? extends BaseServlet> claz = this.getClass();
                className=claz.getName();
                Method method = claz.getDeclaredMethod(methodName, HttpServletRequest.class
                        , HttpServletResponse.class);
                method.invoke(this, request, response);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.printf("错误提示:请检查%s中是否有%s方法%n", className, methodName);
            }
        }
    }
}

package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();//  /brand-case/brand/selectAll

        int index = requestURI.lastIndexOf('/');//  截取路径最后一个斜杠的部分
        String substring = requestURI.substring(index+1);//（截取的路径包含斜杠，因此加1去除斜杠）

        Class<? extends BaseServlet> aClass = this.getClass();//获取字节码文件

        try {
            Method method = aClass.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);//获取方法对象
            method.invoke(this,req,resp);//执行方法
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

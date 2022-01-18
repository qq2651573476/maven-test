package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = brandService.selectAll();

        String s = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        Brand brand = JSON.parseObject(s, Brand.class);

        brandService.add(brand);
        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        int[] ints = JSON.parseObject(s, int[].class);

        brandService.deleteByIds(ints);
        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);

        String s = JSON.toJSONString(brandPageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader reader = req.getReader();
        String s1 = reader.readLine();
        Brand brand = JSON.parseObject(s1, Brand.class);

        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        String s = JSON.toJSONString(brandPageBean);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader reader = req.getReader();
        String s = reader.readLine();

        int id = JSON.parseObject(s, int.class);

        brandService.deleteById(id);
        resp.getWriter().write("success");
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s1 = reader.readLine();
        Integer id = JSON.parseObject(s1, int.class);

        Brand brand = brandService.selectById(id);

        String s = JSON.toJSONString(brand);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);
    }

    public void updateById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);

        brandService.updateById(brand);

        resp.getWriter().write("success");
    }

}

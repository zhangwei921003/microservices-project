package com.purchasing.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Administrator
 * @createTime 2018/9/24
 */
public class BaseServlet extends HttpServlet {


    @Override
    public void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String parameter = req.getParameter("name");
        ServletContext context = getServletContext();
        Object count =context.getAttribute("count");
        System.out.println(count+"=================");
        if(count ==null){
            context.setAttribute("count",context.getInitParameter("count"));
        }else {
            context.setAttribute("count",Integer.parseInt(count.toString())+1);
        }

        System.out.printf("request parameter : [%s]",parameter);
        Enumeration<String> names =  req.getParameterNames();

        String[]  parameters = req.getParameterValues("name");
        for(String str : parameters){
            System.out.printf("request parameters : [%s]",str);
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("Hello,"+parameter);
        writer.write("总浏览量,"+context.getAttribute("count"));
    }
}

package com.purchasing.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangwei
 * @createTime 2018/9/27
 */
@WebServlet(name = "myServlet",
                        value = "/myServlet",
                        initParams =@WebInitParam(name = "name",value = "张维"))
public class MyServlet extends HttpServlet {

    private String value;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        value = config.getInitParameter("name");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //设置响应的编码和响应上下文
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        getServletContext().log("MyServlet is Working！");
        PrintWriter out =response.getWriter();
        out.write("<html><body>Hello,"+value+" !</body></html>");
    }
}

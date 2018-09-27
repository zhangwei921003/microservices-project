package com.purchasing.servlet.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangwei
 * @createTime 2018/9/27
 */
@WebFilter(servletNames="myServlet")
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        ServletContext context = request.getServletContext();
        context.log("URI : "+uri+" is Filter ~~~");
        filterChain.doFilter(request, response);
    }
}

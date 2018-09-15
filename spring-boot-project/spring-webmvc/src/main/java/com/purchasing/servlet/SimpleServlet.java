package com.purchasing.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangwei
 * @createTime 2018/9/10
 */
@WebServlet(value = "/servlet/simple",asyncSupported = true)
public class SimpleServlet  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {

        //设置响应的编码和响应上下文
       resp.setContentType("text/html;charset=utf-8");
       resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        AsyncContext asyncContext = req.startAsync();//startAsync，而不是req.getAsyncContext()
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                writer.println(Thread.currentThread().getName()+"请求完成了");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                writer.println(Thread.currentThread().getName()+"请求超时了");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                writer.println(Thread.currentThread().getName()+"请求错误了");
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                writer.println(Thread.currentThread().getName()+"请求开始了");
            }
        });
        /**
         * 同步调用
         */
//        asyncContext.complete();//通知事件已完成 Calling [asyncRun()] is not valid for a request with Async state [MUST_COMPLETE]
        /**
         * 异步调用
         * 异步Web编程是有场景的
         * 不需要立刻返回，比如Dump数据
         * 不再需要自己写线程池，此处的线程池个数是10个
         * http-nio就是个线程池
         */
        asyncContext.start(()->{
            writer.println(Thread.currentThread().getName()+"事件执行中");
            asyncContext.complete();//通知事件已完成
        });
        /**
         * public interface AsyncListener extends EventListener {
         *     void onComplete(AsyncEvent event) throws IOException;
         *     void onTimeout(AsyncEvent event) throws IOException;
         *     void onError(AsyncEvent event) throws IOException;
         *     void onStartAsync(AsyncEvent event) throws IOException;
         * }
         */
//        PrintWriter writer = resp.getWriter();
//        writer.write("hello,World!");
//        writer.flush();
    }

}

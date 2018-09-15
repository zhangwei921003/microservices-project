package com.purchasing.java.java5;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor 线程池？？ 其实是执行器，线程池是它的一种实现方式
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class ExecutorMain {

    public static void main(String[] args) {
        //执行器，线程池（ThreadPoolExecutor）是它的一种实现
        Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.printf("[ThreadSub : %S ] Starting ...... \n"+"Hello,World" ,Thread.currentThread().getName());
            }
        });

        /**
         * 以上代码执行完成后程序仍然挂起？？
         * 通过shutdown()方法
         * 合理的关闭线程池是非常重要的
         *         ((ExecutorService) executor).shutdown();
         */
        if(executor instanceof  ExecutorService){
            ExecutorService executorService = ExecutorService.class.cast(executor);
            executorService.shutdown();
        }

        //Java 5开始实施 AutoCloseable（自动关闭），I/O ,JDBC都实现了资源的自动关闭，为什么此处实现？


    }
}

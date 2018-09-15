package com.purchasing.java.java5;

import java.util.concurrent.*;

/**
 * Callable 是有返回值操作，相对于Runnable而言
 * V call() throws Exception; Callable
 * public abstract void run(); Runnable
 * 为什么会出现Callable？
 * 1）可执行实现返回值
 * 2）其他？为什么不能再Runnable基础上重构方法，违背原则？
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class CallableMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //执行器，线程池（ThreadPoolExecutor）是它的一种实现
        //线程池是线程复用
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        /**
         * 得到异步操作
         *  Waits if necessary for the computation to complete, and then
         *  retrieves its result
         *  对比Thread.join()方法
         */
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "[Thread :" + Thread.currentThread().getName()+ " , Hello World!]";
            }
        });

        //    Waits if necessary for at most the given time for the computation
        //     to complete, and then retrieves its result, if available.
        System.out.println(future.get());
        executorService.shutdown();
    }
}

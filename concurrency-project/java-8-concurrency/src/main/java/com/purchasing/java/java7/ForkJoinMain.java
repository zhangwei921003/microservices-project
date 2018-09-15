package com.purchasing.java.java7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join示例
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class ForkJoinMain {

    public static void main(String[] args) {

        /**
         * 并行：多核心参与
         * 并发：一同参与
         * ForkJoin 线程池ForkJoinPool
         */
        System.out.printf("当前公用 ForkJoin 线程池并行数： %d\n",ForkJoinPool.commonPool().getParallelism());
        System.out.printf("当前CPU处理器数： %d\n",Runtime.getRuntime().availableProcessors());
        //类似与ThreadPoolExecutor
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /**
         * invoke实现的方式有两种
         * {@link java.util.concurrent.ForkJoinTask} 一般不采用，实现了3个接口
         * {@link  RecursiveAction}
         */
        forkJoinPool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.printf("[Thread : %S ] Starting ...... \n",Thread.currentThread().getName());
            }
        });
        forkJoinPool.shutdown();
    }
}

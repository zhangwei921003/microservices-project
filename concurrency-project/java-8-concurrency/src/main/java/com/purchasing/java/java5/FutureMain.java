package com.purchasing.java.java5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Future的局限性
 * 1）无法手动完成
 * 2）阻塞式结果返回 future.get()
 * 3）无法链式多个Future
 * 4）无法合并多个Future结果
 * <T> List<Future<T>> invokeAll()  获取结果只能遍历，并行改成串行操作
 * 5）缺少异常处理机制
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class FutureMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //执行器，线程池（ThreadPoolExecutor）是它的一种实现
        //线程池是线程复用
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        long startTime = System.currentTimeMillis();
        MultipleTask(executorService);
        SimpleTask(executorService);
        long endTime = System.currentTimeMillis();
        System.out.println("Cost time : "+(endTime-startTime));

        /**
         * {@link ExecutorService#shutdown()}
         * 合理关闭线程池
         */
        executorService.shutdown();

    }

    private static void SimpleTask(ExecutorService executorService) throws ExecutionException, InterruptedException {
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "[Thread :" + Thread.currentThread().getName()+ " , Hello World!]";
            }
        });
        //等待完成
        while (true){
            //是否知道当前线程是否完成
            if(future.isDone()){
                System.out.println("线程执行完成");
                break;
            }
        }
        //Future#get 方法会阻塞当前线程
        //凡是抛出J.U.C框架InterruptedException异常的都是阻塞状态的
        System.out.println(future.get());
    }

    /**
     * 获取多个Future结果只能通过遍历的方式获取搭配
     * @param executorService
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void MultipleTask(ExecutorService executorService) throws InterruptedException, ExecutionException {
        List<FutureTasks>   tasks = new ArrayList<FutureTasks>();
        for(int i = 0;i<50000;i++){
            FutureTasks task= new FutureTasks(i+"");
            tasks.add(task);
        }
        List<Future<String>> futureList =  executorService.invokeAll(tasks);
        for (Future<String> stringFuture :futureList){
            System.out.println(stringFuture.get());
        }
    }

    private static  class FutureTasks implements Callable<String>{

        private String name;

        public FutureTasks(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            return "[Thread :" + Thread.currentThread().getName()+ " , Hello："+name+"]";
        }
    }
}

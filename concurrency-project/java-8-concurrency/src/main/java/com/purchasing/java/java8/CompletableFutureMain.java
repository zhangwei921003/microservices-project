package com.purchasing.java.java8;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link java.util.concurrent.CompletableFuture}
 *
 * @author zhangwei
 * @createTime 2018/9/10
 * @see java.util.concurrent.CompletableFuture
 * @see java.util.concurrent.CompletionStage
 */
public class CompletableFutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 1，完成操作
         *         CompletableFuture<String> completableFuture = new CompletableFuture<String>();
         *         completableFuture.complete("Hello,World!");
         *         System.out.println(completableFuture.get());
         */
        /**
         * 2，异步执行，阻塞操作
         *         CompletableFuture completableFuture = CompletableFuture.runAsync(() ->{
         *             System.out.println("Hello,World!");
         *         });
         *         //阻塞操作
         *         completableFuture.get();
         *
         *         System.out.println("starting ... ");
         */
        /**
         * 3，异步操作
         *         CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
         *             //获取数据操作，假设数据来自于数据库
         *             return String.format("[Thread : %s ] Hello World！ \n",Thread.currentThread().getName());
         *         });
         *         String value = completableFuture.get();
         *         System.out.println("value " + value);
         *         System.out.println("starting ... ");
         */


        //4，异步操作
        CompletableFuture combinedCompletableFuture = CompletableFuture.supplyAsync(() -> {
            //获取数据操作，假设数据来自于数据库
            return String.format("[Thread : %s ] supplyAsync \n", Thread.currentThread().getName());
        }).thenApply(value -> {

            return String.format("[Thread : %s ] thenApply1 "+value + "- 来自于数据库\n", Thread.currentThread().getName());

        }).thenApply(value -> {

            return String.format("[Thread : %s ] thenApply2 "+value + " at " + LocalDate.now()+"\n",Thread.currentThread().getName());

        }).thenApply((value) -> {
            System.out.printf("[Thread : %s ] thenApply3 "+value+"\n",Thread.currentThread().getName());
            return value;
        }).thenRun(() -> {

            System.out.printf("[Thread : %s ] thenRun ，线程结束",Thread.currentThread().getName());

        });

        while (!combinedCompletableFuture.isDone()) {

        }
    }
}

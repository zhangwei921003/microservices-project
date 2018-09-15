package com.purchasing.java9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Flow Java9异步编程
 * @author zhangwei
 * @createTime 2018/9/11
 * {@link Flow.Publisher}
 * {@link Flow.Subscriber}
 * {@link Flow.Subscription}
 * {@link Flow.Processor}
 * {@link SubmissionPublisher#subscribe(Flow.Subscriber)}
 * {@link SubmissionPublisher#submit(Object)}}
 * {@link AutoCloseable#close()}  //
 *Closes this resource, relinquishing any underlying resources.
 * This method is invoked automatically on objects managed by the
 * {@code try}-with-resources statement.
 */
public class SubmissionPublisherDemo {

    public static void main(String[] args) throws InterruptedException {

        try(
            SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()
        ){

            publisher.subscribe(new IntegerSubscribe("第一个订阅者"));
            publisher.subscribe(new IntegerSubscribe("第二个订阅者"));
            publisher.subscribe(new IntegerSubscribe("第三个订阅者"));
            /**
             * [Thread ForkJoinPool.commonPool-worker-2 ， Consume 100  ]
             * [Thread ForkJoinPool.commonPool-worker-1 ， Completed！ ]
             * 可能是不同的线程实现异步操作
             * 如果保证在一个线程中，则调用thenRun()即可
             *[Thread ForkJoinPool.commonPool-worker-3 ， Consume 100  ]
             * [Thread ForkJoinPool.commonPool-worker-3 ， Completed！ ]
             */
            CompletableFuture<Void> completableFuture= publisher.consume((value)->{
                System.out.printf("[Thread %s ， Consume %s  ]\n",Thread.currentThread().getName(), value);
            }).thenRunAsync(()->{//thenRun()调整成同步执行
                System.out.printf("[Thread %s ， Completed！ ]\n",Thread.currentThread().getName());
            });

            //提交到各个订阅器
            publisher.submit(100);
            //publisher.close();onComplete的标识 try(){}可实现此功能 等价于try{}finaly{}
        };
        Thread.currentThread().join();//让线程强停止
    }

    private static class IntegerSubscribe implements Flow.Subscriber<Integer>{

        private Flow.Subscription subscription;
        final private String name;

        private IntegerSubscribe(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.printf("[Thread %s ， onSubscribe %s ]\n",Thread.currentThread().getName(),
                    name);
            (this.subscription = subscription).request(1);
        }

        @Override
        public void onNext(Integer item) {
            subscription.request(1);
            System.out.printf("[Thread %s ， onNext %s ，Item %s ]\n",Thread.currentThread().getName(),
                    name,item);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {
            System.out.printf("[Thread %s ， onComplete %s ]\n",Thread.currentThread().getName(),name);
        }
    }
}

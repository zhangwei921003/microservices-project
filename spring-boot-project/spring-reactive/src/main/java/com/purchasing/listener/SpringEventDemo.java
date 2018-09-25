package com.purchasing.listener;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangwei
 * @createTime 2018/9/25
 */
public class SpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        //默认同步阻塞回调
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        //切换成异步非阻塞回调
        //构建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        multicaster.setTaskExecutor(executorService);
        multicaster.addApplicationListener(event -> {
            System.out.println("当前线程 &  "+Thread.currentThread()+" Event & "+event.getClass().getSimpleName());
        });
        multicaster.multicastEvent(new PayloadApplicationEvent<String>("HelloWorld","HelloWord"));
        context.refresh();
        context.close();
        executorService.shutdown();
    }
}

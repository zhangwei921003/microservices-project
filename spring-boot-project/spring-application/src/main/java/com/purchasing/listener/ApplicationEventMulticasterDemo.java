package com.purchasing.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * @author zhangwei
 * @createTime 2018/9/20
 */
public class ApplicationEventMulticasterDemo {

    public static void main(String[] args) {
        ApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        //添加监听事件（订阅者）
        multicaster.addApplicationListener((event -> {
            if(event instanceof PayloadApplicationEvent){
                System.out.println("监听自定义PayloadApplicationEvent事件："+event);
            }else {
                System.out.println("监听事件："+event);
            }

        }));
        //添加广播器（发布者）
        multicaster.multicastEvent(new MyEvent("Hello World"));
        multicaster.multicastEvent(new PayloadApplicationEvent<>("2","Hello World"));
    }

    private static class MyEvent extends ApplicationEvent{

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyEvent(Object source) {
            super(source);
        }
    }
}

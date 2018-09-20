package com.purchasing.listener;

import org.springframework.context.support.GenericApplicationContext;

/**
 * @author zhangwei
 * @createTime 2018/9/20
 */
public class SpringEventListener {

    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener((event) ->{
            System.out.println(event.getClass().getSimpleName() + " & " +event.getSource().getClass().getName());
        });
        context.refresh();
        context.publishEvent("Hello,World");
        context.close();
    }
}

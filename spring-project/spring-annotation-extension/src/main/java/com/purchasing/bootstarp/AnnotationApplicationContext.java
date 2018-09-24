package com.purchasing.bootstarp;

import com.purchasing.config.UserConfiguration;
import com.purchasing.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @createTime 2018/9/22
 */
public class AnnotationApplicationContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(UserConfiguration.class);
        context.refresh();
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}

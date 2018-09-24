package com.purchasing.bootstarp;

import com.purchasing.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @createTime 2018/9/22
 */
public class XmlApplicationContext {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("classpath:META-INF/spring/springContext.xml");
        context.refresh();
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}

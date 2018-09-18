package com.purchasing;

import com.purchasing.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @createTime 2018/9/17
 */
public class SpringContextTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("SpringContext.xml");
        User user = context.getBean("user",User.class);
        System.out.println(user);
    }
}

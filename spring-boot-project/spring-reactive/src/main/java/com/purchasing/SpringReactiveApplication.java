package com.purchasing;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zhangwei
 * @createTime 2018/9/25
 */
@SpringBootApplication
public class SpringReactiveApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringReactiveApplication.class)
                .run(args)
                .addApplicationListener(event -> {
                    System.out.println(Thread.currentThread().getName()+" Event : "+event.getClass().getSimpleName());
                });
    }
}

package com.purchasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
    public static void main( String[] args )
    {
        //为什么要将App.class传入到类中
        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.run(args);
    }
}

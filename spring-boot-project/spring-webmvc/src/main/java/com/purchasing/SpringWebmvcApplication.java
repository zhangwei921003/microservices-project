package com.purchasing;

import com.purchasing.conf.DefaultHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan("com.purchasing.servlet")//扫描配置的servlet
public class SpringWebmvcApplication implements WebMvcConfigurer ,ErrorPageRegistrar {


    /**
     * 拦截器注册
     * SpringBoot2.0实现WebMvcConfigurer接口
     * 调用addInterceptors将自定义的拦截器注册到容器中即可
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultHandlerInterceptor());
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringWebmvcApplication.class, args);
    }

    /**
     * 异常机制处理
     * SpringBoot2.0实现ErrorPageRegistrar接口
     * 将异常类型ErrorPage(HttpStatus,URL)的处理方式展现
     * @param registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));
//        registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403.html"));
//        registry.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED,"/405.html"));
    }
}

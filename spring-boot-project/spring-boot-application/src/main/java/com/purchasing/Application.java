package com.purchasing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {


        /**
         * SpringApplicationBuilder 作为流动的配置，封装了SpringApplication的操作
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.properties("server.port=0","spring.application.name=application");
        builder.web(WebApplicationType.NONE);
        builder.run(args);
         */

        /**
         * 1，SpringApplication中常见的创建方式有两种
         * 1)  静态方法 run{@link SpringApplication#run(Class, String...)}
         * 2) new SpringApplication对象，{@link SpringApplication#SpringApplication(Class<?>... ) }
         * 2，参数属性设置
         * 1）Map {@link SpringApplication#setDefaultProperties(Map)}
         * 2 ) Properties {@link SpringApplication#setDefaultProperties(Properties)}
         * 3，Web Environment
         * 1）Spring MVC is present, an AnnotationConfigServletWebServerApplicationContext is used
         * 2）Spring MVC is not present and Spring WebFlux is present, an AnnotationConfigReactiveWebServerApplicationContext is used
         * 3）AnnotationConfigApplicationContext is used
         * & 启动初始方法 {@link SpringApplication#deduceWebApplicationType()}
         * & 也可通过set方法改变属性值{@link SpringApplication#setWebApplicationType(WebApplicationType)}
         * 4  Spring Events And Listeners
         * 可以通过监控不同阶段的上下文来实现需要
         *An ApplicationStartingEvent is sent at the start of a run but before any processing, except for the registration of listeners and initializers.
         * An ApplicationEnvironmentPreparedEvent is sent when the Environment to be used in the context is known but before the context is created.
         * An ApplicationPreparedEvent is sent just before the refresh is started but after bean definitions have been loaded.
         * An ApplicationStartedEvent is sent after the context has been refreshed but before any application and command-line runners have been called.
         * An ApplicationReadyEvent is sent after any application and command-line runners have been called. It indicates that the application is ready to service requests.
         * An ApplicationFailedEvent is sent if there is an exception on startup
         *
         * 自定义监听器添加的3中方式
         * ① {@link SpringApplicationBuilder#listeners(ApplicationListener[])}
         * ② {@link SpringApplication#addListeners(ApplicationListener[])}
         * ③ 添加属性值到META-INF/spring.factories
         *      key = org.springframework.context.ApplicationListener
         *      value =com.purchasing.Application.StartedListener,\
         *                   com.purchasing.Application.RefreshedListener,\
         *                   com.purchasing.Application.ClosedListener
         */
        SpringApplication springApplication = new SpringApplication(Application.class);
        Map<String, Object> properties = new LinkedHashMap<>();
        properties.put("server.port", 0);
        springApplication.setDefaultProperties(properties);
//        springApplication.setWebApplicationType(WebApplicationType.REACTIVE);
//        springApplication.addListeners(new StartedListener(),new RefreshedListener(),new ClosedListener());
        Set<ApplicationListener<?>> listeners =  springApplication.getListeners();
        listeners.forEach((listener) ->{
            System.out.println("监听事件："+listener.getClass().getName());
        });
        //StartedListener,RefreshedListener,ClosedListener
        ConfigurableApplicationContext context = springApplication.run(args);
//        context.close();
        System.out.println(context.getClass().getSimpleName());
    }


    private static class StartedListener implements ApplicationListener<ContextStartedEvent>{

        @Override
        public void onApplicationEvent(ContextStartedEvent event) {
            System.out.println("准备上下文：" + event);
        }
    }

    private static class RefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            System.out.println("启动上下文：" + event);
        }
    }

    private static class ClosedListener implements ApplicationListener<ContextClosedEvent> {

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            System.out.println("关闭上下文：" + event);
        }
    }
}

package com.purchasing.reactive;

import com.purchasing.reactive.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 * @author zhangwei
 * @createTime 2018/9/14
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler){
        return route(POST("/web/flux/user/save"),userHandler::save)
                .andRoute(GET("/web/flux/user/list"),userHandler::findAll);
    }
}

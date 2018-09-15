package com.purchasing.conf;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring Web Mvc 异常处理机制
 * @author zhangwei
 * @createTime 2018/9/7
 */
//basePackages指定限定的包范围，如果在同一个包下面则无需指定
@RestControllerAdvice(basePackages = "com.purchasing.controller")
public class ControllerAdvice {
    @ExceptionHandler(value = {NullPointerException.class
            ,IllegalAccessException.class,
            IllegalStateException.class,
    })
    public Object handleException(
            Throwable throwable) {
        Map<String,Object> data = new HashMap<>();
        data.put("message",throwable.getMessage());
        return data;
    }
}

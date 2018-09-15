package com.purchasing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei
 * @createTime 2018/9/7
 */
@RestController //=@Controller + @ResponseBody
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/exception")
    public String exception(){
        throw new NullPointerException("故意抛异常！");
    }

    /**
     * 处理页面找不到的情况
     *
     * @param status
     * @param request
     * @param throwable
     * @return
     */
    @GetMapping("/404.html")
    public Map<String, Object> handlePageNotFound(HttpStatus status,
                                                  HttpServletRequest request,
                                                  Throwable throwable) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("statusCode",request.getAttribute("javax.servlet.error.status_code"));
        errors.put("requestUri", request.getAttribute("javax.servlet.error.request_uri"));
        return errors;
    }

}

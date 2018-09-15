package com.purchasing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangwei
 * @createTime 2018/9/9
 */
@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
}

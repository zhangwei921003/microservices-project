package com.purchasing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @createTime 2018/9/24
 */
@RestController
public class IndexController {

    @GetMapping(value = "index")
    public String index(){
        return "Hello , World ! 张维";
    }
}

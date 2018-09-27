package com.purchasing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei
 * @createTime 2018/9/26
 */
@RestController
public class IndexController {

    @GetMapping(value = "index")
    public Map<String,String> index(){
        Map map = new HashMap();
        map.put("id","1");
        map.put("name","张维");
        return map;
    }
}

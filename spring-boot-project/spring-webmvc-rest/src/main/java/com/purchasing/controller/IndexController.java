package com.purchasing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangwei
 * @createTime 2018/9/9
 */
@Controller
public class IndexController {


    /**
     * 注意ResponseBody和ResponseEntity对应的处理类
     * ResponseBody - RequestResponseBodyMethodProcessor
     * ResponseEntity - HttpEntityMethodProcessor
     * @return
     */
    @ResponseBody
    @GetMapping(value = "demo")
    public String demo(){
        return "demo";
    }


    @GetMapping(value = "cache")
    public ResponseEntity<String> cache(@RequestParam(required = false,defaultValue = "false")boolean cached) {
        ResponseEntity<String> responseEntity = null;
        if(cached){
            return new ResponseEntity<String>("HelloWorld!",HttpStatus.NOT_MODIFIED);
        }else {
            return ResponseEntity.ok("Hello，Cached World!");
        }
    }
}

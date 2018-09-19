package com.purchasing.controller;

import com.purchasing.model.Staff;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangwei
 * @createTime 2018/9/19
 */
@RestController
public class StaffController {

    @PostMapping(value = "/staff/save")
    public Staff save(@Valid @RequestBody Staff staff){
        return staff;
    }
}

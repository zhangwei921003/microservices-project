package com.purchasing.controller;

import com.purchasing.model.User;
import com.purchasing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangwei
 * @createTime 2018/9/14
 */
@RestController
@RequestMapping(value = "/web/mvc/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "save")
    public boolean save(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping(value = "list")
    public List<User> findAll(){
        return repository.findAll();
    }
}

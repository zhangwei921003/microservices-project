package com.purchasing.controller;

import com.purchasing.model.User;
import com.purchasing.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author zhangwei
 * @createTime 2018/9/18
 */
@RestController
public class UserController {

    private  final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/user/save")
    public User user(@RequestParam String name){
        User user = new User();
        user.setName(name);
        repository.save(user);
        return user;
    }

    @GetMapping(value = "/user/list")
    public Collection<User> selectAll(){
        return repository.selectAll();
    }
}

package com.purchasing.controller;

import com.purchasing.model.Person;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangwei
 * @createTime 2018/9/12
 */
@RestController
public class PersonController {

    @GetMapping(value = "/person/{id}")
    public Person personG(@PathVariable Long id,
                         @RequestParam(required = false)String name,
                         @RequestParam(required = false)Integer age){
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        p.setAge(age);
        return p;
    }

    @PostMapping(value = "/person")
    public Person personP(@RequestBody(required = false) Person person){
        return person;
    }


    @PostMapping(value = "/person/properties",produces = "application/properties")
    public Person personProperties(@RequestBody(required = false) Person person){
        return person;
    }

    @PostMapping(value = "/person/jackson",consumes = "application/properties")
    public Person personJackson(@RequestBody(required = false) Person person){
        return person;
    }

}

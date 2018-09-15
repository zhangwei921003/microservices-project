package com.purchasing.model;

import java.util.Date;

/**
 * @author zhangwei
 * @createTime 2018/9/12
 */
public class Person {

    private Long id;
    private String name;
    private Integer age;
    private Date birth = new Date();

    public Person(){}

    public Person(Long id, String name, Integer age, Date birth) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

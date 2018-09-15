package com.purchasing.legacy;

import java.beans.PropertyChangeSupport;

/**
 * 事件监听者模式
 * @author zhangwei
 * @createTime 2018/9/11
 *
 * {@link java.util.EventObject} 标准的是事件对象
 * {@link java.util.EventListener} 标准的事件监听对象
 *
 * Java内省机制
 * {@link java.beans.PropertyChangeEvent} 广播器
 * {@link java.beans.PropertyChangeListener}  注册器
 * {@link java.beans.PropertyChangeSupport}
 *
 */
public class EventListenerDemo {

    public static void main(String[] args) {
        //Java Beans规范 --> 内省机制  java bean 就是 pojo？

        Person p = new Person();
        //注册
        PropertyChangeSupport pcs = new PropertyChangeSupport(p);//注入管理的Bean信息
        pcs.addPropertyChangeListener("name",(event -> {
            System.out.println(event.toString());
        }));
        //触发事件
        pcs.firePropertyChange("name",null,"张维");

    }

    private static class Person{
        private String name;
        private Integer age;

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
    }
}

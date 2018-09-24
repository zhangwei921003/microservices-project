package com.purchasing.config;

import com.purchasing.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @createTime 2018/9/22
 */
@Configuration
public class UserConfiguration {

    @Bean(name = "user")
    public User user(){
        User user = new User();
        user.setName("张维");
        user.setUserId(1L);
        return user;
    }
}

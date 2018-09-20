package com.purchasing;

import com.purchasing.model.User;
import com.purchasing.repository.UserRepository;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)//产生一个测试的随机端口
public class AppTest 
{

    @Autowired
    private UserRepository userRepository;
   @Test
    public void test(){
       User user = new User();
       user.setName("张维");
       System.out.println(userRepository.save(user));
       System.out.println(userRepository.selectAll());
   }
}

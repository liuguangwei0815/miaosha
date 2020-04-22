package com.in.miaosha;

import com.im.miaosha.MainApplication;
import com.im.miaosha.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-17 01:10
 **/
@SpringBootTest(classes = {MainApplication.class})
@RunWith(SpringRunner.class)
public class Test1 {

    @Autowired
    UserService userService;

    @Test
    public void test() {
        System.out.println(userService.getUserById(1));
    }

//    @Autowired
//    private MqSender mqSender;
//
//    @Test
//    public void testMq() {
//        mqSender.send("HAHA");
//    }


}

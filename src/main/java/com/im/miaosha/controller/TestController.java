package com.im.miaosha.controller;

import com.im.miaosha.model.User;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.redis.UserPrefix;
import com.im.miaosha.result.MsgCode;
import com.im.miaosha.result.Result;
import com.im.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: liu.wei
 * @Date: 2020/4/16
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * 测试方法
     *
     * @param model   模型
     * @param request 请求
     * @return {@link String}
     */
    @RequestMapping
    public String thymeleaf(Model model, HttpServletRequest request) {
        model.addAttribute("12221", "a11");
        System.out.println(model.asMap().get("12221"));
        return "hello";
    }

    /**
     * 成功
     *
     * @param model   模型
     * @param request 请求
     * @return {@link Result<User>}
     */
    @RequestMapping("/db")
    @ResponseBody
    public Result<User> getTestDb(Model model, HttpServletRequest request) {
        request.setAttribute("a", userService.getUserById(1));
        System.out.println(userService.getUserById(1));
        return Result.success(userService.getUserById(1));
    }

    @RequestMapping("/db/error")
    @ResponseBody
    public Result<User> getTestErDb(Model model, HttpServletRequest request) {
        return Result.fail(MsgCode.SERVER_ERROR);
    }


    @Autowired
    private RedisServer redisServer;

    /**
     * 从redis获取获取对象
     *
     * @param model   模型
     * @param request 请求
     * @return {@link Result<User>}
     */
    @RequestMapping("/redis/use")
    @ResponseBody
    public Result<User> getUserKey(Model model, HttpServletRequest request) {
        return Result.success(redisServer.getValue(UserPrefix.getById, String.valueOf(1), User.class));
    }

    /**
     * 设置用户的信息在redis
     *
     * @param model   模型
     * @param request 请求
     * @return {@link Result<User>}
     */
    @RequestMapping("/redis/user/set")
    @ResponseBody
    public Result<Boolean> setUserKey(Model model, HttpServletRequest request) {
        User user = userService.getUserById(1);
        // 设置用户信息到redis
        return Result.success(redisServer.setKey(UserPrefix.getById, String.valueOf(1), user));
    }

}

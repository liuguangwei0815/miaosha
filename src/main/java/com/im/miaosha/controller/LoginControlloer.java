package com.im.miaosha.controller;

import com.im.miaosha.dto.LoginDto;
import com.im.miaosha.result.Result;
import com.im.miaosha.service.MiaoshaUserServier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @program: miaosha
 * @description: 登录
 * @author: liu.wei
 * @create: 2020-04-18 09:53
 **/
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginControlloer {

    @Autowired
    private MiaoshaUserServier miaoshaUserServier;

    /**
     * 登录
     *
     * @param model   模型
     * @param request 请求
     * @return {@link String}
     */
    @RequestMapping
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public Result doLogin(Model model, HttpServletRequest request, @Valid LoginDto loginDto, HttpServletResponse response) {
        System.out.println("LoginDto===>" + loginDto);
        miaoshaUserServier.login(response,loginDto);
        return Result.success(true);
    }

}

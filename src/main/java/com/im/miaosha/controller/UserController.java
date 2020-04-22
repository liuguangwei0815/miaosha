package com.im.miaosha.controller;

import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 *
 * @author liuwei
 * @date 2020/04/20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 查询用户信息
     *
     * @param miaoshaUser miaosha用户
     * @return {@link Result<MiaoshaUser>}
     */
    @GetMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> getuserinfo(MiaoshaUser miaoshaUser){
        return Result.success(miaoshaUser);
    }


}

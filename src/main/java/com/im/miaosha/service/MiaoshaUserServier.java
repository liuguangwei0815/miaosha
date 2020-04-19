package com.im.miaosha.service;

import com.im.miaosha.dto.LoginDto;
import com.im.miaosha.model.MiaoshaUser;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 12:37
 **/
public interface MiaoshaUserServier {

    public final static String COOKIE_NAME_TOKEN = "cookie_name_token";

    /**
     * 登录
     *
     * @param response 响应
     * @param loginDto 登录dto
     * @return boolean
     */
    public boolean login(HttpServletResponse response, LoginDto loginDto);

    /**
     *  添加或者延迟分布式session信息
     *
     * @param token    令牌
     * @param user     用户
     * @param response 响应
     */
    public void addOrDelayByToken(String token, MiaoshaUser user, HttpServletResponse response);

}

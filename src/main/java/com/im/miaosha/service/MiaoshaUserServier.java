package com.im.miaosha.service;

import com.im.miaosha.dto.LoginDto;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 12:37
 **/
public interface MiaoshaUserServier {


    /**
     * 登录
     *
     * @param response 响应
     * @param loginDto 登录dto
     * @return boolean
     */
    public boolean login(HttpServletResponse response, LoginDto loginDto);

}

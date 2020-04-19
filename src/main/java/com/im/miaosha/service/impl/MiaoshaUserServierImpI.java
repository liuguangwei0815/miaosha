package com.im.miaosha.service.impl;

import com.im.miaosha.dao.MiaoshaUserMapper;
import com.im.miaosha.dto.LoginDto;
import com.im.miaosha.exception.BusinessException;
import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.redis.MiaoShaUserPrefix;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.result.MsgCode;
import com.im.miaosha.service.MiaoshaUserServier;
import com.im.miaosha.utils.MD5Utils;
import com.im.miaosha.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-18 12:40
 **/
@Service
public class MiaoshaUserServierImpI implements MiaoshaUserServier {

    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;
    @Autowired
    private RedisServer redisServer;


    @Override
    public boolean login(HttpServletResponse response, LoginDto loginDto) {
        //获取表单第一md5密码
        String password = loginDto.getPassword();
        MiaoshaUser user = miaoshaUserMapper.getUserByUserPhone(loginDto.getMobile());
        if (user == null) {
            //抛出异常让全局异常处理进行处理
            throw new BusinessException(MsgCode.USERDOESNOTEXIST);
        }
        String dbpw = user.getPassword();
        //这个会在注册的时候进行了加密了的
        String debSalt = user.getSalt();
        String convertdbPassword = MD5Utils.fromPassToDbPass(loginDto.getPassword(), debSalt);
        if (!StringUtils.equals(dbpw, convertdbPassword)) {
            throw new BusinessException(MsgCode.PASSWORDDOESNOTMATCH);
        }
        //将我们的session放到我们的redis上面，cookie 会传递给我们下一个请求
        String token = UUIDUtils.getUID();
        createSessionAndCookie(token, user, response);
        return true;
    }

    @Override
    public void addOrDelayByToken(String token,MiaoshaUser user ,HttpServletResponse response) {
         createSessionAndCookie(token,user,response);
    }

    /**
     * 将用户信息存储在redis cookie 存放token （其实还是一个延迟登录状态的方法）
     *
     * @param token    令牌
     * @param user     用户
     * @param response 响应
     */
    private void createSessionAndCookie(String token, MiaoshaUser user, HttpServletResponse response) {
        redisServer.setKey(MiaoShaUserPrefix.token, token, user);
        //设置我们的cookie 将我们的token放到我们的response 返回给浏览器
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoShaUserPrefix.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}

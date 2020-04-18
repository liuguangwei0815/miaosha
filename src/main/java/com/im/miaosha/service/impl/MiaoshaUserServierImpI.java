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
        //将我们的session放到我们的redis上面

        String token = UUIDUtils.getUID();
        redisServer.setKey(MiaoShaUserPrefix.token, token, user);
        //设置我们的cookie 将我们的token放到我们的response 返回给浏览器
        Cookie cookie = new Cookie("cookie_name_token",token);
        cookie.setMaxAge(MiaoShaUserPrefix.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("12121");
        System.out.println("12121");
        System.out.println("12121");
        System.out.println("12121");
        return true;
    }
}

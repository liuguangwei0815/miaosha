package com.im.miaosha.mvcconofig;

import com.im.miaosha.model.MiaoshaUser;
import com.im.miaosha.redis.MiaoShaUserPrefix;
import com.im.miaosha.redis.RedisServer;
import com.im.miaosha.service.MiaoshaUserServier;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miaosha
 * @description: 方法参数赋值
 * @author: liu.wei
 * @create: 2020-04-19 11:17
 **/
@Component
public class DistributedSessionConfig implements HandlerMethodArgumentResolver {

    @Autowired
    private RedisServer redisServer;
    @Autowired
    private MiaoshaUserServier miaoshaUserServier;

    /**
     * 支持参数 只有参数等于这种类型才会进行下一步进行resolveArgument
     *
     * @param parameter 参数
     * @return boolean
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return parameterType == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取request
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        //获取response
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        //获取token request获取
        String paramToken = request.getParameter(MiaoshaUserServier.COOKIE_NAME_TOKEN);
        //获取token cookie获取
        String cookieToken = getTokenByCookie(request);
        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(paramToken)) {
            //如果获取不了什么也不做
            return null;
        }
        String token = StringUtils.isBlank(cookieToken) ? paramToken : cookieToken;
        return getByToken(token, response);
    }

    private MiaoshaUser getByToken(String token, HttpServletResponse response) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        MiaoshaUser user = redisServer.getValue(MiaoShaUserPrefix.token, token, MiaoshaUser.class);
        //延迟登录时间
        if (user != null) {
            miaoshaUserServier.addOrDelayByToken(token, user, response);
        }
        return user;
    }

    private String getTokenByCookie(HttpServletRequest request) {
        //变量所有的cookie如果name相等 返回
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), MiaoshaUserServier.COOKIE_NAME_TOKEN)) {
                return cookie.getValue();
            }
        }
        //如果没有null
        return null;
    }
}

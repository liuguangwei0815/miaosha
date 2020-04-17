package com.im.miaosha.service;


import com.im.miaosha.model.User;

/**
 * @program: miaosha
 * @description: 用户Service
 * @author: liu.wei
 * @create: 2020-04-17 00:56
 **/
public interface UserService {

    /**
     * 得到用户的id
     *
     * @param userId 用户id
     * @return {@link User}
     */
    public User getUserById(Integer userId);
}

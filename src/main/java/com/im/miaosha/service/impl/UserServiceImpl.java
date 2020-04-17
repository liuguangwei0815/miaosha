package com.im.miaosha.service.impl;


import com.im.miaosha.dao.UserMapper;
import com.im.miaosha.model.User;
import com.im.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务impl
 *
 * @author liuwei
 * @date 2020/04/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUerById(userId);
    }
}

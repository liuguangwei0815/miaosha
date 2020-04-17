package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description: 用户前缀
 * @author: liu.wei
 * @create: 2020-04-17 21:05
 **/
public class UserPrefix extends BasePrefix {
    public UserPrefix(String prefix) {
        super(prefix);
    }

    /**
     * 用户模块模块过期时间为永久所以这里直接传递key 不进行时间参数设置
     */
    public static UserPrefix getById = new UserPrefix("id");
    public static UserPrefix getByName = new UserPrefix("name");

}

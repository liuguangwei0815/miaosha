package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-17 21:07
 **/
public class OrderPrefix extends  BasePrefix {
    public OrderPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}

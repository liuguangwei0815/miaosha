package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description: key的前缀
 * @author: liu.wei
 * @create: 2020-04-17 20:48
 **/
public interface KeyPrefix {

    /**
     * 到期秒
     *
     * @return int
     */
    public int expireSeconds();

    /**
     * 得到的前缀
     *
     * @return {@link String}
     */
    public String getPrefix();
}

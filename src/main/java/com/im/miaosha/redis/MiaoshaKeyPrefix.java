package com.im.miaosha.redis;

public class MiaoshaKeyPrefix extends BasePrefix {
    public MiaoshaKeyPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static final MiaoshaKeyPrefix MIAOSHAKEYPREFIX = new MiaoshaKeyPrefix(30, "msk");
}

package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description: 秒杀用户前缀
 * @author: liu.wei
 * @create: 2020-04-17 21:05
 **/
public class MiaoShaUserPrefix extends BasePrefix {
    //设置两天时间
    private static final int expireTime = 3600 * 24 * 2;

    public MiaoShaUserPrefix(int expire, String prefix) {
        super(expire,prefix);
    }

    /**
     * 用户模块模块过期时间为永久所以这里直接传递key 不进行时间参数设置
     */
    public static MiaoShaUserPrefix token = new MiaoShaUserPrefix(expireTime, "kt");
}

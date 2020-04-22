package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description: 商品列表缓存prefix
 * @author: liu.wei
 * @create: 2020-04-17 21:05
 **/
public class GoodsPrefix extends BasePrefix {
    public GoodsPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsPrefix GOODLIST = new GoodsPrefix(30,"glist");
    public static GoodsPrefix GDETAIL = new GoodsPrefix(30,"gdetail");

}

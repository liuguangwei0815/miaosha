package com.im.miaosha.redis;

/**
 * @program: miaosha
 * @description:
 * @author: liu.wei
 * @create: 2020-04-17 20:50
 **/
public abstract class BasePrefix implements KeyPrefix {
    /**
     * 到期秒
     */
    private int expireSeconds;
    /**
     * 前缀
     */
    private String prefix;

    public BasePrefix(String prefix) {
        //0代表永不过期
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    /**
     * 到期秒 默认0代表永不过期
     *
     * @return int
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        //子类实现被调用 这里的simpleName 就是子类的名称了
        String simpleName = this.getClass().getSimpleName();
        return simpleName + ":" + prefix;
    }
}

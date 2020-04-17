package com.im.miaosha.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: miaosha
 * @description: redis服务
 * @author: liu.wei
 * @create: 2020-04-17 16:49
 **/
@Service
@Slf4j
public class RedisServer {

    @Autowired
    private JedisPool jedisPool;


    /**
     * 得到value
     *
     * @param key    关键
     * @param tClass t类
     * @return {@link T}
     */
    public <T> T getValue(KeyPrefix keyPrefix, String key, Class<T> tClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成的key
            String realKey = keyPrefix.getPrefix() + key;
            log.info("get value for realKey：{}", realKey);
            String str = jedis.get(realKey);
            T t = stringToObject(str, tClass);
            return t;
        } finally {
            //用完了需要关闭
            returnPool(jedis);
        }
    }

    /**
     * 设置键
     *
     * @param keyPrefix 关键的前缀
     * @param key       关键
     * @param t         t
     * @return boolean
     */
    public <T> boolean setKey(KeyPrefix keyPrefix, String key, T t) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = objectToString(t);
            //如果没有值 那么不进行设置操作
            if (StringUtils.isBlank(str)) {
                return false;
            }
            //生成的key
            String realKey = keyPrefix.getPrefix() + key;
            log.info("set value for realKey：{}", realKey);

            int seconds = keyPrefix.expireSeconds();
            if (keyPrefix.expireSeconds() == 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            //用完了需要关闭
            returnPool(jedis);
        }
    }


    /**
     * 存在
     *
     * @param keyPrefix 关键的前缀
     * @param key       关键
     * @return boolean
     */
    public <T> boolean exists(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成的key
            String realKey = keyPrefix.getPrefix() + key;
            log.info("set value for realKey：{}", realKey);
            return jedis.exists(realKey);
        } finally {
            //用完了需要关闭
            returnPool(jedis);
        }
    }


    /**
     * 增加
     *
     * @param keyPrefix 关键的前缀
     * @param key       关键
     * @return {@link Long}
     */
    public <T> Long incr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成的key
            String realKey = keyPrefix.getPrefix() + key;
            log.info("set value for realKey：{}", realKey);
            return jedis.incr(realKey);
        } finally {
            //用完了需要关闭
            returnPool(jedis);
        }
    }

    /**
     * 减少
     *
     * @param keyPrefix 关键的前缀
     * @param key       关键
     * @return {@link Long}
     */
    public <T> Long decr(KeyPrefix keyPrefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成的key
            String realKey = keyPrefix.getPrefix() + key;
            log.info("set value for realKey：{}", realKey);
            return jedis.decr(realKey);
        } finally {
            //用完了需要关闭
            returnPool(jedis);
        }
    }


    /**
     * 返回池
     *
     * @param resource 资源
     */
    private void returnPool(Jedis resource) {
        if (resource != null) {
            resource.close();
        }
    }

    /**
     * 对象转字符串类型
     *
     * @param t t
     * @return {@link String}
     */
    private static <T> String objectToString(T t) {
        if (t == null) {
            return null;
        }
        Class<?> aClass = t.getClass();
        if (aClass == int.class || aClass == Integer.class || aClass == long.class || aClass == Long.class) {
            return "" + aClass;
        } else if (aClass == String.class) {
            return (String) t;
        } else {
            return JSON.toJSONString(t);
        }
    }

    /**
     * 字符串转为对象
     *
     * @param str str
     * @return {@link T}
     */
    private <T> T stringToObject(String str, Class<T> aClass) {
        if (StringUtils.isBlank(str) || aClass == null) {
            return null;
        }
        if (aClass == int.class || aClass == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (aClass == long.class || aClass == Long.class) {
            return (T) Long.valueOf(str);
        } else if (aClass == String.class) {
            return (T) str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), aClass);
        }
    }
}

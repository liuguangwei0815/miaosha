package com.im.miaosha.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: miaosha
 * @description:111
 * @author: liu.wei
 * @create: 2020-04-17 18:48
 **/
@Configuration
@Slf4j
public class JedisPoolFactory {
    @Autowired
    private RedisConfig redisConfig;

    /**
     * jedis工厂
     *
     * @return {@link JedisPool}
     */
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        //因为我们在配置文件进行配置的秒 但是最大等待时间是毫秒 所以需要转换
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jedisPool;
    }
}

package com.im.miaosha.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: miaosha
 * @description: redis配置
 * @author: liu.wei
 * @create: 2020-04-17 16:44
 **/
@Component
@ConfigurationProperties(prefix = "reids")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private String password;
    private int timeout;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}

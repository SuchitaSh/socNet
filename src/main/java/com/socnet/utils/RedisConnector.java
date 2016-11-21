package com.socnet.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

@Component
public class RedisConnector {

    @Resource
    private Environment env;

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        if (jedisPool == null) {
            jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.3.99");
        }
        return jedisPool;
    }
}

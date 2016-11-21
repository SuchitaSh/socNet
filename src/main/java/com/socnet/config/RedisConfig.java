package com.socnet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * @author Ruslan Lazin
 */
@Configuration
public class RedisConfig {

    @Autowired
    MessageSource messageSource;

    @Bean
    public JedisPool getJedisPool() {
        return new JedisPool(new JedisPoolConfig(),
                messageSource.getMessage("redis.url", null, null),
                Protocol.DEFAULT_PORT,
                Protocol.DEFAULT_TIMEOUT,
                messageSource.getMessage("redis.password", null, null));
    }
}

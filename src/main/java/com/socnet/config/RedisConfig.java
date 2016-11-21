package com.socnet.config;

import com.socnet.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
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

    @Bean
    public RedisSerializer<Message> getRedisSerializer() {
        return new Jackson2JsonRedisSerializer<Message>(Message.class);
    }
}

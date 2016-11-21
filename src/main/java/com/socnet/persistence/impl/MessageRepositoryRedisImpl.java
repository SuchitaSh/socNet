package com.socnet.persistence.impl;

import com.socnet.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @author Ruslan Lazin
 */

@Component
public class MessageRepositoryRedisImpl implements MessageRepository {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void addMessage(String key, String text) {
        Jedis jedis = jedisPool.getResource();
        jedis.lpush(key, text);
        jedis.close();
    }

    @Override
    public List<String> getAllMessages(String key) {
        Jedis jedis = jedisPool.getResource();
        List<String> messages = jedis.lrange(key, 0, -1);
        jedis.close();
        return messages;
    }

    @Override
    public List<String> getLastMessages(String key, int quantity) {
        Jedis jedis = jedisPool.getResource();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Tried to read " + quantity + " messages.");
        }
        List<String> messages = jedis.lrange(key, 0, quantity - 1);
        jedis.close();
        return messages;
    }
}

package com.socnet.persistence.impl;

import com.socnet.persistence.repository.MessageRepository;
import com.socnet.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ruslan Lazin
 */

@Component
public class MessageRepositoryRedisImpl implements MessageRepository {
    private JedisPool jedisPool;
    private RedisSerializer<Message> redisSerializer;

    @Autowired
    public MessageRepositoryRedisImpl(JedisPool jedisPool, RedisSerializer<Message> redisSerializer) {
        this.jedisPool = jedisPool;
        this.redisSerializer = redisSerializer;
    }

    @Override
    public void addMessage(String key, Message message) {
        Jedis jedis = jedisPool.getResource();
        jedis.lpush(key.getBytes(), redisSerializer.serialize(message));
        jedis.close();
    }

    @Override
    public List<Message> getAllMessages(String key) {
        Jedis jedis = jedisPool.getResource();
        List<byte[]> rows = jedis.lrange(key.getBytes(), 0, -1);
        jedis.close();
        if (rows.isEmpty()) return null;
        return rows.stream().map(row -> redisSerializer.deserialize(row)).collect(Collectors.toList());
    }

    @Override
    public List<Message> getLastMessages(String key, int quantity) {
        Jedis jedis = jedisPool.getResource();
        if (quantity <= 0) {
            throw new IllegalArgumentException("Tried to read " + quantity + " messages.");
        }
        List<byte[]> rows = jedis.lrange(key.getBytes(), 0, -1);
        jedis.close();
        if (rows.isEmpty()) return null;
        return rows.stream().map(row -> redisSerializer.deserialize(row)).collect(Collectors.toList());
    }
}

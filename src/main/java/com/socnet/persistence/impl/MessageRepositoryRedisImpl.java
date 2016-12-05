package com.socnet.persistence.impl;

import com.socnet.persistence.repository.MessageRepository;
import com.socnet.persistence.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.support.incrementer.HsqlSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ruslan Lazin
 */

@Component
public class MessageRepositoryRedisImpl implements MessageRepository {
    private JedisPool jedisPool;
    private RedisSerializer<Message> redisSerializer;
       
    private static String createUnreadMessagesCounterKey(String receiver){
    	return "unread:messages:" + receiver;
    	}
    
    @Autowired
    public MessageRepositoryRedisImpl(JedisPool jedisPool, RedisSerializer<Message> redisSerializer) {
        this.jedisPool = jedisPool;
        this.redisSerializer = redisSerializer;
    }

    @Override
    public void addMessage(String key, Message message) {
        Jedis jedis = jedisPool.getResource();
        jedis.rpush(key.getBytes(), redisSerializer.serialize(message));
 
        if (!message.isRead()){
        	String unreadMessagesKey = createUnreadMessagesCounterKey(message.getReceiver());
        	jedis.hincrBy(unreadMessagesKey, message.getSender(), 1);
        }
        
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
        List<byte[]> rows = jedis.lrange(key.getBytes(), -quantity, -1);
        jedis.close();
        if (rows.isEmpty()) return null;
        return rows.stream().map(row -> redisSerializer.deserialize(row)).collect(Collectors.toList());
    }
    
    @Override
    public int getUnreadMessagesCount(String receiver) {
    	int count = 0;
    	
    	try(Jedis jedis = jedisPool.getResource()){
    		String key = createUnreadMessagesCounterKey(receiver);
    		count = jedis.hgetAll(key).values()
    										 .stream()
    										 .filter(s -> s != null)
    										 .mapToInt(s -> Integer.valueOf(s)) 
    										 .sum();
    	}
    	
    	return count;
    }
    
    @Override
    public int getUnreadMessagesCountPerSender(String sender, String receiver) {
    	int count = 0;
    	
    	try(Jedis jedis = jedisPool.getResource()){
    		String key = createUnreadMessagesCounterKey(receiver);
    		String value = jedis.hget(key, sender);
    		if(value != null){
    			count = Integer.valueOf(value);
    		}
    	}
    	
    	return count;
    }
    
    @Override
    public void setUnreadMessagesCountToNull(String sender, String receiver) {
    	try(Jedis jedis = jedisPool.getResource()){
    		String key = createUnreadMessagesCounterKey(receiver);
    		jedis.hset(key, sender, "0");
    	}
    	
    }
}

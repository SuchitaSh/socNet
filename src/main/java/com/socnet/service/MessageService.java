package com.socnet.service;

import com.socnet.persistence.repository.MessageRepository;
import com.socnet.utils.Message;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ruslan Lazin
 */

@Component
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message) {
        String key = makeKey(message.getSender(), message.getDestination());
        messageRepository.add(key, message);
    }

    public List<Message> getAllMessages(String participantOneUserName, String participantTwoUserName) {
        String key = makeKey(participantOneUserName, participantTwoUserName);
        return messageRepository.getAll(key);
    }

    public List<Message> getLastMessages(String participantOneUserName,
                                         String participantTwoUserName,
                                         int quantity) {
        String key = makeKey(participantOneUserName, participantTwoUserName);
        return messageRepository.getLast(key, quantity);
    }

    private String makeKey(String senderUsername, String receiverUserName) {
        if (senderUsername.compareTo(receiverUserName) < 0) {
            return senderUsername + ":" + receiverUserName;
        }
        return receiverUserName + ":" + senderUsername;
    }
}

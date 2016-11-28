package com.socnet.service;

import com.socnet.persistence.repository.MessageRepository;
import com.socnet.persistence.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Ruslan Lazin
 */
@Component
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message) {
        String key = makeKey(message.getSender(), message.getReceiver());
        messageRepository.addMessage(key, message);
    }

    public List<Message> getAllMessages(String participantOneUserName, String participantTwoUserName) {
        String key = makeKey(participantOneUserName, participantTwoUserName);
        return messageRepository.getAllMessages(key);
        }

    public List<Message> getLastMessages(String senderUsername, String receiverUserName, int quantity) {
        String key = makeKey(senderUsername, receiverUserName);
        return messageRepository.getLastMessages(key, quantity);
    }

    private String makeKey(String senderUsername, String receiverUserName) {
        if (senderUsername.compareTo(receiverUserName) < 0) {
            return senderUsername + ":" + receiverUserName;
        }
        return receiverUserName + ":" + senderUsername;
    }
}

package com.socnet.service;

import com.socnet.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void addMessage(String senderUsername, String receiverUserName, String text) {
        String key = makeKey(senderUsername, receiverUserName);
        messageRepository.addMessage(key, text);
    }

    public List<String> getAllMessages(String senderUsername, String receiverUserName) {
        String key = makeKey(senderUsername, receiverUserName);
        return messageRepository.getAllMessages(key);
    }

    public List<String> getLastMessages(String senderUsername, String receiverUserName, int quantity) {
        String key = makeKey(senderUsername, receiverUserName);
        return messageRepository.getLastMessages(key, quantity);
    }

    private String makeKey(String senderUsername, String receiverUserName) {
        if (senderUsername.compareTo(receiverUserName) > 0) {
            return senderUsername + ":" + receiverUserName;
        }
        return receiverUserName + ":" + senderUsername;
    }
}

package com.socnet.service;

import com.socnet.persistence.repository.MessageRepository;
import com.socnet.utils.Message;
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
        String key = makeKey(message.getSender(), message.getDestination());
        messageRepository.addMessage(key, message.getMessage());
    }

    public List<Message> getAllMessages(String participantOneUserName, String participantTwoUserName) {
        String key = makeKey(participantOneUserName, participantTwoUserName);
        List<Message> messages = new ArrayList<>();
        Message m = new Message();
        m.setDestination(participantOneUserName);
        m.setSender(participantTwoUserName);
        m.setMessage("stub");
        messages.add(m);
        return messages;
    }

//    public List<String> getLastMessages(String senderUsername, String receiverUserName, int quantity) {
//        String key = makeKey(senderUsername, receiverUserName);
//        return messageRepository.getLastMessages(key, quantity);
//    }

    private String makeKey(String senderUsername, String receiverUserName) {
        if (senderUsername.compareTo(receiverUserName) < 0) {
            return senderUsername + ":" + receiverUserName;
        }
        return receiverUserName + ":" + senderUsername;
    }
}

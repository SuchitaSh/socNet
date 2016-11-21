package com.socnet.persistence.repository;

import com.socnet.utils.Message;

import java.util.List;

/**
 * @author Ruslan Lazin
 */
public interface MessageRepository {
    void add(String key, Message message);

    List<Message> getAll(String key);

    List<Message> getLast(String key, int quantity);
}

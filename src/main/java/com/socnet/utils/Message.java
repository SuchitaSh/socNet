package com.socnet.utils;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 4907958828438185177L;
    private String message;
    private String sender;
    private String destination;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!message.equals(message1.message)) return false;
        if (!sender.equals(message1.sender)) return false;
        return destination.equals(message1.destination);
    }

    @Override
    public int hashCode() {
        int result = message.hashCode();
        result = 31 * result + sender.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}

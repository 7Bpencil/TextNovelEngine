package com.urfu.chadnovelengine.backendapi;

import java.util.Objects;

public class Message {
    public final String content;
    public final MessageType messageType;

    public Message(String content, MessageType messageType) {
        this.content = content;
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return content.equals(message.content) && messageType == message.messageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, messageType);
    }
}

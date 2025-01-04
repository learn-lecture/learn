package org.demo.chatservice.chat.ui.dto;

import org.demo.chatservice.chat.repository.entities.Message;

public record ChatMessage(
        String sender,
        String message
) {

    public static ChatMessage from(Message message) {
        return new ChatMessage(message.getMember().getName(), message.getText());
    }

}

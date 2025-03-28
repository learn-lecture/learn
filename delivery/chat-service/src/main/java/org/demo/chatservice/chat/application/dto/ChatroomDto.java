package org.demo.chatservice.chat.application.dto;

import java.time.LocalDateTime;
import org.demo.chatservice.chat.repository.entities.Chatroom;

public record ChatroomDto(Long id, String title, Boolean hasNewMessage, Integer memberCount, LocalDateTime createdAt) {

    public static ChatroomDto from(Chatroom chatroom) {
        return new ChatroomDto(
                chatroom.getId(),
                chatroom.getTitle(),
                chatroom.getHasNewMessage(),
                chatroom.getMemberChatroomMappings().size(),
                chatroom.getCreatedAt()
        );
    }

}

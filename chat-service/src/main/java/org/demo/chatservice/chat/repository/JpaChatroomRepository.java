package org.demo.chatservice.chat.repository;

import org.demo.chatservice.chat.repository.entities.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatroomRepository extends JpaRepository<Chatroom, Long> {
}

package org.demo.chatservice.chat.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.demo.chatservice.chat.repository.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByChatroomId(Long chatroomId);

    Boolean existsByChatroomIdAndCreatedAtAfter(Long chatroomId, LocalDateTime lastCheckedAt);

}

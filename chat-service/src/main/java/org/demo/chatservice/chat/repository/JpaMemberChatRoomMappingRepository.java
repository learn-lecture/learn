package org.demo.chatservice.chat.repository;

import org.demo.chatservice.chat.repository.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberChatRoomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {
}

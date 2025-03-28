package org.demo.chatservice.chat.repository;

import java.util.List;
import java.util.Optional;
import org.demo.chatservice.chat.repository.entities.MemberChatroomMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberChatRoomMappingRepository extends JpaRepository<MemberChatroomMapping, Long> {

    boolean existsByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    void deleteByMemberIdAndChatroomId(Long memberId, Long chatroomId);

    List<MemberChatroomMapping> findAllByMemberId(Long memberId);

    Optional<MemberChatroomMapping> findByMemberIdAndChatroomId(Long memberId, Long currentChatroomId);
}

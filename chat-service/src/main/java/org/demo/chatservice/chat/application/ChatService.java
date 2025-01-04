package org.demo.chatservice.chat.application;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.chat.repository.JpaChatroomRepository;
import org.demo.chatservice.chat.repository.JpaMemberChatRoomMappingRepository;
import org.demo.chatservice.chat.repository.entities.Chatroom;
import org.demo.chatservice.chat.repository.entities.MemberChatroomMapping;
import org.demo.chatservice.oauth.repository.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final JpaChatroomRepository jpaChatroomRepository;
    private final JpaMemberChatRoomMappingRepository jpaMemberChatRoomMappingRepository;

    @Transactional
    public Chatroom createChatroom(Member member, String title) {
        log.info("create chatroom {}", member);
        Chatroom chatroom = Chatroom.builder()
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();

        chatroom = jpaChatroomRepository.save(chatroom);

        MemberChatroomMapping memberChatroomMapping = chatroom.addMember(member);
        jpaMemberChatRoomMappingRepository.save(memberChatroomMapping);

        return chatroom;
    }

    public Boolean joinChatroom(Member member, Long chatroomId) {
        if (jpaMemberChatRoomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("Chatroom {} already exists", chatroomId);
            return false;
        }

        Chatroom chatroom = jpaChatroomRepository.findById(chatroomId).orElse(null);
        MemberChatroomMapping memberChatroomMapping = MemberChatroomMapping.builder()
                .member(member)
                .chatroom(chatroom)
                .build();

        jpaMemberChatRoomMappingRepository.save(memberChatroomMapping);

        return true;
    }

    @Transactional
    public Boolean leaveChatroom(Member member, Long chatroomId) {
        if (!jpaMemberChatRoomMappingRepository.existsByMemberIdAndChatroomId(member.getId(), chatroomId)) {
            log.info("Chatroom {} does not exist", chatroomId);
            return false;
        }

        jpaMemberChatRoomMappingRepository.deleteByMemberIdAndChatroomId(member.getId(), chatroomId);
        return true;
    }

    public List<Chatroom> getChatroom(Member member) {
        List<MemberChatroomMapping> chatroomMappings =
                jpaMemberChatRoomMappingRepository.findAllByMemberId(member.getId());

        return chatroomMappings.stream()
                .map(MemberChatroomMapping::getChatroom)
                .toList();
    }

}

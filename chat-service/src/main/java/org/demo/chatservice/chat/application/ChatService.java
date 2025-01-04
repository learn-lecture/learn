package org.demo.chatservice.chat.application;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.chat.repository.JpaChatroomRepository;
import org.demo.chatservice.chat.repository.JpaMemberChatRoomMappingRepository;
import org.demo.chatservice.chat.repository.JpaMessageRepository;
import org.demo.chatservice.chat.repository.entities.Chatroom;
import org.demo.chatservice.chat.repository.entities.MemberChatroomMapping;
import org.demo.chatservice.chat.repository.entities.Message;
import org.demo.chatservice.oauth.repository.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final JpaChatroomRepository jpaChatroomRepository;
    private final JpaMemberChatRoomMappingRepository jpaMemberChatRoomMappingRepository;
    private final JpaMessageRepository jpaMessageRepository;

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
        return jpaMemberChatRoomMappingRepository.findAllByMemberId(member.getId())
                .stream()
                .map(memberChatroomMapping -> {
                    Chatroom chatroom = memberChatroomMapping.getChatroom();
                    chatroom.setHasNewMessage(jpaMessageRepository.existsByChatroomIdAndCreatedAtAfter(
                            chatroom.getId(),
                            memberChatroomMapping.getLastCheckedAt()
                    ));
                    return chatroom;
                })
                .toList();
    }

    public Message saveMessage(Member member, Long chatroomId, String text) {
        Chatroom chatroom = jpaChatroomRepository.findById(chatroomId).get();
        Message message = Message.builder()
                .text(text)
                .member(member)
                .chatroom(chatroom)
                .createdAt(LocalDateTime.now())
                .build();
        return jpaMessageRepository.save(message);
    }

    public List<Message> getMessages(Long chatroomId) {
        return jpaMessageRepository.findAllByChatroomId(chatroomId);
    }

}

package org.demo.chatservice.chat.ui;

import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.chat.application.ChatService;
import org.demo.chatservice.chat.application.dto.ChatroomDto;
import org.demo.chatservice.chat.repository.entities.Chatroom;
import org.demo.chatservice.chat.ui.dto.ChatMessage;
import org.demo.chatservice.oauth.domain.CustomOauth2User;
import org.demo.chatservice.member.repository.entities.Member;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chats/{chatroomId}")
    @SendTo("/sub/chats/{chatroomId}")
    public ChatMessage handleMessage(
            @AuthenticationPrincipal Principal principal,
            @DestinationVariable Long chatroomId,
            @Payload Map<String, String> payload
    ) {
        CustomOauth2User user = (CustomOauth2User) ((AbstractAuthenticationToken) principal).getPrincipal();
        Member member = user.getMember();
        String text = payload.get("message");

        chatService.saveMessage(member, chatroomId, text);
        messagingTemplate.convertAndSend("/sub/chats/updates", chatService.getChatroom(chatroomId));
        log.info("{} sent {} in {}", member.getName(), text, chatroomId);

        return new ChatMessage(member.getName(), text);
    }

}

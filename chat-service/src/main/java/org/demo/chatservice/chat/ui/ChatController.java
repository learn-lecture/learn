package org.demo.chatservice.chat.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.chat.application.ChatService;
import org.demo.chatservice.chat.application.dto.ChatroomDto;
import org.demo.chatservice.chat.repository.entities.Chatroom;
import org.demo.chatservice.chat.ui.dto.ChatMessage;
import org.demo.chatservice.oauth.domain.CustomOauth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatroomDto createChatroom(
            @AuthenticationPrincipal CustomOauth2User user,
            @RequestParam String title
    ) {
        return ChatroomDto.from(chatService.createChatroom(user.getMember(), title));
    }

    @PostMapping("/{chatroomId}")
    public Boolean joinChatRoom(
            @AuthenticationPrincipal CustomOauth2User user,
            @PathVariable Long chatroomId,
            @RequestParam(required = false) Long currentChatroomId
    ) {
        return chatService.joinChatroom(user.getMember(), chatroomId, currentChatroomId);
    }

    @DeleteMapping("/{chatroomId}")
    public Boolean leaveChatRoom(
            @AuthenticationPrincipal CustomOauth2User user,
            @PathVariable Long chatroomId
    ) {
        return chatService.leaveChatroom(user.getMember(), chatroomId);
    }

    @GetMapping
    public List<ChatroomDto> getChatroom(@AuthenticationPrincipal CustomOauth2User user) {
        return chatService.getChatroom(user.getMember())
                .stream()
                .map(ChatroomDto::from)
                .toList();
    }

    @GetMapping("/{chatroomId}/messages")
    public List<ChatMessage> getChatroomMessages(
            @AuthenticationPrincipal CustomOauth2User user,
            @PathVariable Long chatroomId
    ) {
        return chatService.getMessages(chatroomId)
                .stream()
                .map(ChatMessage::from)
                .toList();
    }

}

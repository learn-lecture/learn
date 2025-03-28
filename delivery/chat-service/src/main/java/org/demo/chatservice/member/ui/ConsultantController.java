package org.demo.chatservice.member.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.chat.application.dto.ChatroomDto;
import org.demo.chatservice.member.application.ConsultantService;
import org.demo.chatservice.member.application.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consultants")
@RequiredArgsConstructor
@Slf4j
public class ConsultantController {

    private final ConsultantService consultantService;

    @PostMapping
    @ResponseBody
    public MemberDto createConsultant(@RequestBody MemberDto dto) {
        return consultantService.saveMember(dto);
    }

    @GetMapping
    public String index() {
        return "/consultants/index.html";
    }

    @GetMapping("/chats")
    @ResponseBody
    public Page<ChatroomDto> getChatroomPage(@PageableDefault(size = 5) Pageable pageable) {
        return consultantService.getChatroomPage(pageable);
    }

}

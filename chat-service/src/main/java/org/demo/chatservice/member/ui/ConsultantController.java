package org.demo.chatservice.member.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.demo.chatservice.member.application.CustomUserDetailsService;
import org.demo.chatservice.member.application.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultants")
@RequiredArgsConstructor
@Slf4j
public class ConsultantController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping
    @ResponseBody
    public MemberDto createConsultant(@RequestBody MemberDto dto) {
        return customUserDetailsService.saveMember(dto);
    }

    @GetMapping
    public String index() {
        return "/consultants/index.html";
    }

}

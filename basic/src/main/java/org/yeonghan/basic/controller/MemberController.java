package org.yeonghan.basic.controller;

import org.springframework.stereotype.Controller;
import org.yeonghan.basic.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

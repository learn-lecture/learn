package org.study.auth.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.auth.application.AuthService;
import org.study.auth.application.dto.LoginRequestDto;
import org.study.auth.application.dto.UserAccessTokenResponseDto;
import org.study.common.ui.Response;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        return Response.ok(authService.login(dto));
    }

}

package org.delivery.api.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.controller.model.info.UserDtoStatus;
import org.delivery.common.api.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness business;

    @PostMapping("/register")
    public ResponseEntity<Api<UserResponse>> register(@Valid @RequestBody final UserRegisterRequest request) {
        final UserResponse response = business.register(request);
        return ResponseEntity.ok(Api.ok(UserDtoStatus.USER_REGISTERED_SUCCESS, response));
    }

    @PostMapping("/login")
    public ResponseEntity<Api<TokenResponse>> login(@Valid @RequestBody final UserLoginRequest request) {
        final TokenResponse response = business.login(request);
        return ResponseEntity.ok(Api.ok(UserDtoStatus.USER_LOGIN_SUCCESS, response));
    }

}

package org.delivery.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.controller.model.info.UserDtoStatus;
import org.delivery.common.annotation.UserSession;
import org.delivery.common.api.Api;
import org.delivery.db.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public ResponseEntity<Api<UserResponse>> me(@UserSession final User user) {
        final UserResponse response = userBusiness.me(user);
        return ResponseEntity.ok(Api.ok(UserDtoStatus.USER_PROFILE_SUCCESS, response));
    }

}

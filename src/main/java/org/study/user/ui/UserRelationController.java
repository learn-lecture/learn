package org.study.user.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.common.ui.Response;
import org.study.user.application.UserRelationService;
import org.study.user.application.dto.FollowUserRequestDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relation")
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody @Valid FollowUserRequestDto dto) {
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    public Response<Void> unfollowUser(@RequestBody @Valid FollowUserRequestDto dto) {
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }

}

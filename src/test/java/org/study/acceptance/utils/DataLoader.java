package org.study.acceptance.utils;

import static org.study.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.study.acceptance.steps.UserAcceptanceSteps.followUser;

import org.springframework.stereotype.Component;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.application.dto.FollowUserRequestDto;

@Component
public class DataLoader {

    public void loadData() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

}

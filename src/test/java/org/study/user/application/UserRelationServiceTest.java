package org.study.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.application.dto.FollowUserRequestDto;
import org.study.user.domain.User;
import org.study.user.repository.FakeUserRepository;
import org.study.user.repository.FakerUserRelationRepository;
import org.study.user.repository.UserRelationRepository;
import org.study.user.repository.UserRepository;

public class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserRelationRepository userRelationRepository = new FakerUserRelationRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto followUserRequestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);
        this.followUserRequestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(followUserRequestDto);

        // then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(followUserRequestDto);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(followUserRequestDto));
    }

    @Test
    void givenCreateOneUser_whenFollow_thenUserThrowError() {
        // given
        FollowUserRequestDto me = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(me));
    }

    @Test
    void givenCreateTwoUserFollowed_whenUnfollow_thenUserUnfollowSaved() {
        // given
        userRelationService.follow(followUserRequestDto);

        // when
        userRelationService.unfollow(followUserRequestDto);

        // then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError() {
        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(followUserRequestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollow_thenUserThrowError() {
        // given
        FollowUserRequestDto me = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(me));
    }

}

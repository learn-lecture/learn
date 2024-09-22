package org.study.acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.study.acceptance.steps.FeedAcceptanceSteps.requestCreatePost;
import static org.study.acceptance.steps.FeedAcceptanceSteps.requestFeed;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.study.acceptance.utils.AcceptanceTestTemplate;
import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.domain.content.PostPublicationState;
import org.study.post.ui.dto.GetPostContentResponseDto;

class FeedAcceptanceTest extends AcceptanceTestTemplate {

    /**
     * User 1 --- follow ---> User 2
     * User 1 --- follow ---> User 3
     */
    @BeforeEach
    void setUp() {
        super.init();
    }

    /**
     * User 2 create Post 1
     * User 1 Get Post 1 From Feed
     */
    @Test
    void givenUserHasFollowerAndCreatePostWhenFollowerUserRequestFeedThenFollowerCanGetPostFromFeed() {
        // given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = requestCreatePost(dto);

        // when
        List<GetPostContentResponseDto> result = requestFeed(1L);

        // then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

}

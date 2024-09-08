package org.study.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.study.fake.FakeObjectFactory;
import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.application.dto.LikeRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.domain.Post;
import org.study.post.domain.content.Content;
import org.study.post.domain.content.PostPublicationState;
import org.study.user.application.UserService;
import org.study.user.application.dto.CreateUserRequestDto;
import org.study.user.domain.User;

public class PostServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final PostService postService = FakeObjectFactory.getPostService();

    private final User user = userService.createUser(new CreateUserRequestDto("user1", null));;
    private final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));;

    private final CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(dto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // given
        Post savedPost = postService.createPost(dto);
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(), user.getId(), "updated-content", PostPublicationState.PRIVATE);

        // when
        Post updatedPost = postService.updatePost(updateDto);

        // then
        String content = updatedPost.getContent();
        assertEquals("updated-content", content);
        assertEquals(PostPublicationState.PRIVATE, updatedPost.getState());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        // when
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnliked_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenUnlikedTwice_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(likeRequestDto);

        // when
        postService.unlikePost(likeRequestDto);
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnlikedTwice_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

}

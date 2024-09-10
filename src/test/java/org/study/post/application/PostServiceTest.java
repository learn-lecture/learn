package org.study.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.study.post.application.dto.LikeRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.domain.Post;
import org.study.post.domain.content.PostPublicationState;

public class PostServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post savedPost = postService.createPost(postDto);

        // then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // given
        Post savedPost = postService.createPost(postDto);
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(user.getId(), "updated-content", PostPublicationState.PRIVATE);

        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), updateDto);

        // then
        String content = updatedPost.getContent();
        assertEquals("updated-content", content);
        assertEquals(PostPublicationState.PRIVATE, updatedPost.getState());
    }

    @Test
    void givenCreatedPost_whenLiked_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        // when
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostLiked_whenLikedTwice_thenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(postDto);
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
        Post savedPost = postService.createPost(postDto);
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
        Post savedPost = postService.createPost(postDto);
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
        Post savedPost = postService.createPost(postDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(savedPost.getId(), otherUser.getId());

        // when
        postService.unlikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

}

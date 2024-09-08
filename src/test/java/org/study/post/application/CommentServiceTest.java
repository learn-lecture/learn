package org.study.post.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.study.fake.FakeObjectFactory;
import org.study.post.application.dto.CreateCommentRequestDto;
import org.study.post.application.dto.LikeRequestDto;
import org.study.post.application.dto.UpdateCommentRequestDto;
import org.study.post.domain.comment.Comment;

public class CommentServiceTest extends PostApplicationTestTemplate {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final String commentContent = "this is test comment";

    CreateCommentRequestDto commentDto = new CreateCommentRequestDto(post.getId(), user.getId(), commentContent);

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        // when
        Comment comment = commentService.createComment(commentDto);

        // then
        String content = comment.getContent();
        assertEquals(commentContent, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment comment = commentService.createComment(commentDto);
        String updatedCommentContent = "this is updated comment";
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), updatedCommentContent);

        // when
        Comment updatedComment = commentService.updateComment(updateCommentRequestDto);

        // then
        String content = updatedComment.getContent();
        assertSame(comment, updatedComment);
        assertEquals(updatedCommentContent, content);
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike() {
        // given
        Comment comment = commentService.createComment(commentDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());

        // when
        commentService.likeComment(likeRequestDto);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentLiked_whenUnlikeComment_thenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(commentDto);
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        // when
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentWhenLikeSelfThenThrowException() {
        // given
        Comment comment = commentService.createComment(commentDto);

        // when, then
        LikeRequestDto likeRequestDto = new LikeRequestDto(comment.getId(), user.getId());
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeRequestDto));
    }

}

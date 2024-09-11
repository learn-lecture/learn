package org.study.post.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.common.ui.Response;
import org.study.post.application.CommentService;
import org.study.post.application.dto.CreateCommentRequestDto;
import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.application.dto.UpdateCommentRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.domain.Post;
import org.study.post.domain.comment.Comment;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updatePost(
        @PathVariable Long commentId,
        @RequestBody UpdateCommentRequestDto dto
    ) {
        Comment comment = commentService.updateComment(commentId, dto);
        return Response.ok(comment.getId());
    }

}

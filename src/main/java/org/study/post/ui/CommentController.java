package org.study.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
import org.study.post.application.dto.LikeRequestDto;
import org.study.post.application.dto.UpdateCommentRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.application.interfaces.CommentRepository;
import org.study.post.domain.Post;
import org.study.post.domain.comment.Comment;
import org.study.post.repository.comment.CommentQueryRepository;
import org.study.post.ui.dto.GetContentResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentQueryRepository commentQueryRepository;

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

    @PostMapping("/like")
    public Response<Void> likeComment(@RequestBody LikeRequestDto dto) {
        commentService.likeComment(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Void> unlikeComment(@RequestBody LikeRequestDto dto) {
        commentService.unlikeComment(dto);
        return Response.ok(null);
    }

    @GetMapping("/post/{postId}/{userId}")
    public Response<List<GetContentResponseDto>> getPostComments(
        @PathVariable Long postId,
        @PathVariable Long userId,
        Long lastCommentId
    ) {
        List<GetContentResponseDto> response = commentQueryRepository.getComments(postId, userId, lastCommentId);
        return Response.ok(response);
    }

}

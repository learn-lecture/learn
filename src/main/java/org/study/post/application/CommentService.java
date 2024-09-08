package org.study.post.application;

import org.study.post.application.dto.CreateCommentRequestDto;
import org.study.post.application.dto.LikeRequestDto;
import org.study.post.application.dto.UpdateCommentRequestDto;
import org.study.post.application.interfaces.CommentRepository;
import org.study.post.application.interfaces.LikeRepository;
import org.study.post.domain.Post;
import org.study.post.domain.comment.Comment;
import org.study.user.application.UserService;
import org.study.user.domain.User;

public class CommentService {

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, LikeRepository likeRepository,
        UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(IllegalAccessError::new);
    }

    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(null, post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto) {
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }

    public void likeComment(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            return;
        }
        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikePost(LikeRequestDto dto) {
        Comment comment = getComment(dto.targetId());
        User user = userService.getUser(dto.userId());

        if (likeRepository.checkLike(comment, user)) {
            comment.unlike();
            likeRepository.unlike(comment, user);
        }
    }

}

package org.study.post.application.interfaces;

import java.util.Optional;
import org.study.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);

}

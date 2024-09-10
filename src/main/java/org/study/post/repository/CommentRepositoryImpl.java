package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.post.application.interfaces.CommentRepository;
import org.study.post.domain.comment.Comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return null;
    }

}

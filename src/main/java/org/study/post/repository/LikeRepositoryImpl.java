package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.study.post.application.interfaces.LikeRepository;
import org.study.post.domain.Post;
import org.study.post.domain.comment.Comment;
import org.study.user.domain.User;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    @Override
    public boolean checkLike(Post post, User user) {
        return false;
    }

    @Override
    public void like(Post post, User user) {

    }

    @Override
    public void unlike(Post post, User user) {

    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        return false;
    }

    @Override
    public void like(Comment comment, User user) {

    }

    @Override
    public void unlike(Comment comment, User user) {

    }

}

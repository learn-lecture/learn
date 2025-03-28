package org.study.post.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.message.application.interfaces.FcmMessageRepository;
import org.study.post.application.interfaces.LikeRepository;
import org.study.post.domain.Post;
import org.study.post.domain.comment.Comment;
import org.study.post.repository.entity.comment.CommentEntity;
import org.study.post.repository.entity.like.LikeEntity;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.jpa.JpaCommentRepository;
import org.study.post.repository.jpa.JpaLikeRepository;
import org.study.post.repository.jpa.JpaPostRepository;
import org.study.user.domain.User;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaLikeRepository jpaLikeRepository;
    private final FcmMessageRepository fcmMessageRepository;

    @Override
    public boolean checkLike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.save(likeEntity);
        jpaPostRepository.updateLikeCount(post.getId(), 1);
        fcmMessageRepository.sendLikeMessage(user, post.getAuthor());
    }

    @Override
    @Transactional
    public void unlike(Post post, User user) {
        LikeEntity likeEntity = new LikeEntity(post, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaPostRepository.updateLikeCount(post.getId(), -1);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.save(likeEntity);
        jpaCommentRepository.updateLikeCount(comment.getId(), 1);
    }

    @Override
    @Transactional
    public void unlike(Comment comment, User user) {
        LikeEntity likeEntity = new LikeEntity(comment, user);
        jpaLikeRepository.deleteById(likeEntity.getId());
        jpaCommentRepository.updateLikeCount(comment.getId(), -1);
    }

}

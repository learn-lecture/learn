package org.study.post.repository.postqueue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.entity.post.UserPostQueueEntity;
import org.study.post.repository.jpa.JpaPostRepository;
import org.study.post.repository.jpa.JpaUserPostQueueRepository;
import org.study.user.repository.entity.UserEntity;
import org.study.user.repository.jpa.JpaUserRelationRepository;
import org.study.user.repository.jpa.JpaUserRepository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());

        List<UserPostQueueEntity> userPostQueueEntities = followersIds.stream()
            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), userEntity.getId()))
            .toList();

        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<Long> postsIds = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        List<UserPostQueueEntity> userPostQueueEntities = postsIds.stream()
            .map(postId -> new UserPostQueueEntity(userId, postId, targetId))
            .toList();
        jpaUserPostQueueRepository.saveAll(userPostQueueEntities);
    }

    @Override
    @Transactional
    public void deleteUnFollowPost(Long userId, Long targetId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }

}
package org.study.post.repository.postqueue;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.jpa.JpaPostRepository;
import org.study.user.repository.entity.UserEntity;
import org.study.user.repository.jpa.JpaUserRelationRepository;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository {

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final UserQueueRedisRepository redisRepository;

    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        UserEntity userEntity = postEntity.getAuthor();
        List<Long> followersIds = jpaUserRelationRepository.findFollowers(userEntity.getId());
        redisRepository.publishPostToFollowingUsers(postEntity, followersIds);
    }

    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        List<PostEntity> postEntities = jpaPostRepository.findAllPostIdsByAuthorId(targetId);
        redisRepository.publishPostsToFollowerUsers(postEntities, userId);
    }

    @Override
    @Transactional
    public void deleteUnFollowPost(Long userId, Long targetId) {
        redisRepository.deleteFeed(userId, targetId);
    }

}
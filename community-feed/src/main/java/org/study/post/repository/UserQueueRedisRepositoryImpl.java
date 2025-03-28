package org.study.post.repository;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.postqueue.UserQueueRedisRepository;

@Repository
@Profile({"!test"})
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    @Override
    public void publishPostToFollowingUsers(PostEntity postEntity, List<Long> userIds) {

    }

    @Override
    public void publishPostsToFollowerUsers(List<PostEntity> postEntities, Long userId) {

    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {

    }

}

package org.study.post.repository.postqueue;

import java.util.List;
import org.study.post.repository.entity.post.PostEntity;

public interface UserQueueRedisRepository {

    void publishPostToFollowingUsers(PostEntity postEntity, List<Long> userIds);
    void publishPostsToFollowerUsers(List<PostEntity> postEntities, Long userId);
    void deleteFeed(Long userId, Long authorId);

}

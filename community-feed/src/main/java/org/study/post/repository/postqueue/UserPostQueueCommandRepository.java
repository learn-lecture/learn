package org.study.post.repository.postqueue;

import org.study.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteUnFollowPost(Long userId, Long targetId);

}

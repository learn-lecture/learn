package org.study.post.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.postqueue.UserQueueRedisRepository;

@Repository
@Profile("test")
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUsers(PostEntity postEntity, List<Long> userIds) {
        for (Long userId: userIds) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostsToFollowerUsers(List<PostEntity> postEntities, Long userId) {
        if (queue.containsKey(userId)){
            queue.get(userId).addAll(postEntities);
        } else {
            queue.put(userId, new HashSet<>(postEntities));
        }
    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {
        if (queue.containsKey(userId)) {
            queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(authorId));
        }
    }

    public List<PostEntity> getPostsByUserId(Long userId) {
        return List.copyOf(queue.get(userId));
    }

}

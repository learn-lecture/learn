package org.study.post.repository;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.study.post.repository.entity.post.PostEntity;
import org.study.post.repository.postqueue.UserPostQueueQueryRepository;
import org.study.post.ui.dto.GetPostContentResponseDto;

@Profile("test")
@Repository
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    @Autowired
    private FakeUserQueueRedisRepository redisRepository;

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntities = redisRepository.getPostsByUserId(userId);
        List<GetPostContentResponseDto> result = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            GetPostContentResponseDto res = GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .build();
            result.add(res);
        }
        return result;
    }

}

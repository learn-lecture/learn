package org.study.post.repository;

import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.study.post.repository.postqueue.UserPostQueueQueryRepository;
import org.study.post.ui.dto.GetPostContentResponseDto;

@Repository
@Profile({"!test"})
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        return List.of();
    }

}

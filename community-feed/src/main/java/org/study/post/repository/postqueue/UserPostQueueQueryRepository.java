package org.study.post.repository.postqueue;

import java.util.List;
import org.study.post.ui.dto.GetPostContentResponseDto;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId);

}

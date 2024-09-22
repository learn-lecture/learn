package org.study.post.repository.comment;

import java.util.List;
import org.study.post.ui.dto.GetContentResponseDto;

public interface CommentQueryRepository {

    List<GetContentResponseDto> getComments(Long postId, Long userId, Long lastContentId);

}
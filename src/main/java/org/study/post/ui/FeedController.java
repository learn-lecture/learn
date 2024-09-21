package org.study.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.common.ui.Response;
import org.study.post.repository.postqueue.UserPostQueueQueryRepository;
import org.study.post.ui.dto.GetPostContentResponseDto;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
        @PathVariable Long userId,
        Long lastPostId
    ) {
        List<GetPostContentResponseDto> response = queueQueryRepository.getContentResponse(userId, lastPostId);
        return Response.ok(response);
    }

}

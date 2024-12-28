package org.study.post.ui;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.common.principle.AuthPrinciple;
import org.study.common.principle.UserPrinciple;
import org.study.common.ui.Response;
import org.study.post.repository.postqueue.UserPostQueueQueryRepository;
import org.study.post.ui.dto.GetPostContentResponseDto;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

    private final UserPostQueueQueryRepository queueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(
        @AuthPrinciple UserPrinciple principle,
        Long lastPostId
    ) {
        return Response.ok(queueQueryRepository.getContentResponse(principle.getUserId(), lastPostId));
    }

}

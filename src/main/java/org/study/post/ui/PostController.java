package org.study.post.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.common.ui.Response;
import org.study.post.application.PostService;
import org.study.post.application.dto.CreatePostRequestDto;
import org.study.post.application.dto.UpdatePostRequestDto;
import org.study.post.domain.Post;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(
        @PathVariable Long postId,
        @RequestBody UpdatePostRequestDto dto
    ) {
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

}

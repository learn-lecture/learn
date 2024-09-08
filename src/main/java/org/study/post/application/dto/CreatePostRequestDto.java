package org.study.post.application.dto;

import org.study.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long memberId, String content, PostPublicationState state) {
}

package org.study.post.application.dto;

import org.study.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {
}

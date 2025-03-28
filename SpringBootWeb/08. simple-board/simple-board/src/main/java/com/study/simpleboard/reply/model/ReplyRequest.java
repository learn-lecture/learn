package com.study.simpleboard.reply.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ReplyRequest(
	@NotNull
	Long postId,

	@NotBlank
	String userName,

	@NotBlank
	String password,

	@NotBlank
	String title,

	@NotBlank
	String content
) {
}

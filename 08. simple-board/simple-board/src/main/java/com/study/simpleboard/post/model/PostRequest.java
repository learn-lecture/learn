package com.study.simpleboard.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostRequest(
	@NotNull
	Long boardId,

	@NotBlank
	String userName,

	@NotBlank
	@Size(min = 4, max = 4)
	String password,

	@NotBlank
	@Email
	String email,

	@NotBlank
	String title,

	@NotBlank
	String content
) {
}

package com.study.simpleboard.board.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BoardRequest(
	@NotBlank
	String boardName
) {
}

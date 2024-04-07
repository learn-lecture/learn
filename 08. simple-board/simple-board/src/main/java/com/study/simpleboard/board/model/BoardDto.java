package com.study.simpleboard.board.model;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.model.PostDto;

import lombok.Builder;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record BoardDto(
	Long id,

	String boardName,

	String status,

	List<PostDto> posts
) {
	public static BoardDto toDto(final BoardEntity board) {
		return BoardDto.builder()
			.boardName(board.getBoardName())
			.id(board.getId())
			.status(board.getStatus())
			.posts(postsConverter(board.getPosts()))
			.build();
	}

	public static List<PostDto> postsConverter(final List<PostEntity> posts) {
		return posts.stream()
			.map(PostDto::toDto)
			.toList();
	}
}

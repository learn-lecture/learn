package com.study.simpleboard.post.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.model.BoardDto;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.model.ReplyDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record PostDto(
	Long id,
	Long boardId,
	String userName,
	String password,
	String email,
	String status,
	String title,
	String content,
	LocalDateTime postedAt,
	List<ReplyDto> replies
) {

	public static PostDto toDto(final PostEntity post) {
		return PostDto.builder()
			.id(post.getId())
			.boardId(post.getBoard().getId())
			.userName(post.getUserName())
			.password(post.getPassword())
			.email(post.getEmail())
			.status(post.getStatus())
			.title(post.getTitle())
			.content(post.getContent())
			.postedAt(post.getPostedAt())
			.replies(repliesConverter(post.getReplies()))
			.build();
	}

	public static List<ReplyDto> repliesConverter(final List<ReplyEntity> replies) {
		return replies.stream()
			.map(ReplyDto::toDto)
			.toList();
	}

}

package com.study.simpleboard.reply.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.reply.db.ReplyEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record ReplyDto(
	Long id,
	Long postId,
	String userName,
	String password,
	String status,
	String title,
	String content,
	LocalDateTime repliedAt
) {

	public static ReplyDto toDto(final ReplyEntity reply) {
		return ReplyDto.builder()
			.id(reply.getId())
			.postId(reply.getPost().getId())
			.userName(reply.getUserName())
			.password(reply.getPassword())
			.status(reply.getStatus())
			.title(reply.getTitle())
			.content(reply.getContent())
			.repliedAt(reply.getRepliedAt())
			.build();
	}

}

package com.study.simpleboard.reply.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.study.simpleboard.curd.Converter;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.db.PostRepository;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.model.ReplyDto;
import com.study.simpleboard.reply.model.ReplyRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDto, ReplyEntity> {

	private final PostRepository postRepository;

	@Override
	public ReplyDto toDto(final ReplyEntity replyEntity) {
		return ReplyDto.toDto(replyEntity);
	}

	@Override
	public ReplyEntity toEntity(final ReplyDto replyDto, final String status) {
		final PostEntity post = postRepository.findById(replyDto.postId())
			.orElseThrow(()-> {
				throw new IllegalArgumentException("Not Found");
			});

		final ReplyEntity entity = ReplyEntity.builder()
			.post(post)
			.userName(replyDto.userName())
			.password(replyDto.password())
			.status(status)
			.title(replyDto.title())
			.content(replyDto.content())
			.repliedAt(isReplied(replyDto.repliedAt()))
			.build();

		return entity;
	}

	@Override
	public ReplyEntity delete(final ReplyEntity replyEntity) {
		replyEntity.setStatus("UNREGISTERED");
		return replyEntity;
	}

	@Override
	public ReplyEntity toEntity(final ReplyDto replyDto) {
		final PostEntity post = postRepository.findById(replyDto.postId())
			.orElseThrow(()-> {
				throw new IllegalArgumentException("Not Found");
			});

		final ReplyEntity entity = ReplyEntity.builder()
			.post(post)
			.userName(replyDto.userName())
			.password(replyDto.password())
			.status(replyDto.status())
			.title(replyDto.title())
			.content(replyDto.content())
			.repliedAt(isReplied(replyDto.repliedAt()))
			.build();

		return entity;
	}

	private LocalDateTime isReplied(final LocalDateTime time) {
		if (time == null) {
			return LocalDateTime.now();
		}
		return time;
	}

}

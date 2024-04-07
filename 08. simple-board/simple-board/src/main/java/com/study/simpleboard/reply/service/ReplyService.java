package com.study.simpleboard.reply.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.db.ReplyRepository;
import com.study.simpleboard.reply.model.ReplyRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;

	public ReplyEntity create(final ReplyRequest replyRequest) {
		final ReplyEntity registered = ReplyEntity.builder()
			.postId(replyRequest.postId())
			.userName(replyRequest.userName())
			.password(replyRequest.password())
			.status("REGISTERED")
			.title(replyRequest.title())
			.content(replyRequest.content())
			.repliedAt(LocalDateTime.now())
			.build();

		return replyRepository.save(registered);
	}

	public List<ReplyEntity> findAllByPostId(final Long postId) {
		return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
	}
}

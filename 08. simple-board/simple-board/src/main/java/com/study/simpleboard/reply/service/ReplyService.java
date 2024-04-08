package com.study.simpleboard.reply.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.db.PostRepository;
import com.study.simpleboard.post.service.PostService;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.db.ReplyRepository;
import com.study.simpleboard.reply.model.ReplyDto;
import com.study.simpleboard.reply.model.ReplyRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;

	public ReplyDto create(final ReplyRequest replyRequest) {
		final PostEntity post = postRepository.findById(replyRequest.postId())
			.orElseThrow(()-> {
				throw new IllegalArgumentException("Not FOund");
			});

		final ReplyEntity registered = ReplyEntity.builder()
			.post(post)
			.userName(replyRequest.userName())
			.password(replyRequest.password())
			.status("REGISTERED")
			.title(replyRequest.title())
			.content(replyRequest.content())
			.repliedAt(LocalDateTime.now())
			.build();

		return ReplyDto.toDto(replyRepository.save(registered));
	}

	public List<ReplyEntity> findAllByPostId(final Long postId) {
		return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
	}
}

package com.study.simpleboard.reply.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.study.simpleboard.common.Api;
import com.study.simpleboard.curd.CRUDAbstractService;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.model.ReplyDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService extends CRUDAbstractService<ReplyDto, ReplyEntity> {
	@Override
	public Api<List<ReplyDto>> list(final Pageable pageable) {
		final Api<List<ReplyDto>> pages = super.list(pageable);

		final List<ReplyDto> replies = pages.body().stream()
			.filter(it -> it.status().equals("REGISTERED"))
			.toList();

		return new Api<>(replies, pages.pagination());
	}

	@Override
	public ReplyDto read(final Long id) {
		return super.read(id).validation();
	}

	/*
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
	}*/
}

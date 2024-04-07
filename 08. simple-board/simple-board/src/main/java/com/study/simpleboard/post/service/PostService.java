package com.study.simpleboard.post.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.db.BoardRepository;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.db.PostRepository;
import com.study.simpleboard.post.model.PostDto;
import com.study.simpleboard.post.model.PostRequest;
import com.study.simpleboard.post.model.PostViewRequest;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final ReplyService replyService;
	private final BoardRepository boardRepository;

	public PostDto create(final PostRequest postRequest) {
		final BoardEntity board = boardRepository.findById(postRequest.boardId())
			.orElseThrow(() -> {
				throw new IllegalArgumentException("wrong board");
			});

		final PostEntity registered = PostEntity.builder()
			.board(board)
			.userName(postRequest.userName())
			.password(postRequest.password())
			.email(postRequest.email())
			.status("REGISTERED")
			.title(postRequest.title())
			.content(postRequest.content())
			.postedAt(LocalDateTime.now())
			.build();

		return PostDto.toDto(postRepository.save(registered));
	}

	// Todo : Reply 연관관계 설정하기
	public PostEntity view(final PostViewRequest request) {
		final PostEntity entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(request.postId(), "REGISTERED")
			.orElseThrow(() -> {
				throw new IllegalArgumentException("not found");
			});

		if (!entity.getPassword().equals(request.password())) {
			throw new IllegalArgumentException("wrong passwd");
		}

		final List<ReplyEntity> replies = replyService.findAllByPostId(request.postId());
		entity.setReplies(replies);

		return entity;
	}

	public List<PostDto> all() {
		return postRepository.findAll().stream()
			.filter(it -> it.getStatus().equals("REGISTERED"))
			.map(PostDto::toDto)
			.toList();
	}

	public void delete(final PostViewRequest request) {
		final PostEntity entity = postRepository.findById(request.postId())
			.orElseThrow(() -> {
				throw new IllegalArgumentException("not found");
			});
		if (!entity.getPassword().equals(request.password())) {
			throw new IllegalArgumentException("wrong passwd");
		}

		entity.setStatus("UNREGISTERED");
		postRepository.save(entity);

	}
}

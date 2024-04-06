package com.study.simpleboard.post.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.db.PostRepository;
import com.study.simpleboard.post.model.PostRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public PostEntity create(final PostRequest postRequest) {
		final PostEntity registered = PostEntity.builder()
			.boardId(1L)
			.userName(postRequest.userName())
			.password(postRequest.password())
			.email(postRequest.email())
			.status("REGISTERED")
			.title(postRequest.title())
			.content(postRequest.content())
			.postedAt(LocalDateTime.now())
			.build();

		return postRepository.save(registered);
	}

}

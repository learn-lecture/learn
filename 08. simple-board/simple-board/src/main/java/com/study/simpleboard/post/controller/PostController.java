package com.study.simpleboard.post.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.common.Api;
import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.model.PostDto;
import com.study.simpleboard.post.model.PostRequest;
import com.study.simpleboard.post.model.PostViewRequest;
import com.study.simpleboard.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("")
	public PostDto create(@Valid @RequestBody final PostRequest postRequest) {
		return postService.create(postRequest);
	}

	@PostMapping("/view")
	public PostDto view(@Valid @RequestBody final PostViewRequest postViewRequest) {
		return postService.view(postViewRequest);
	}

	@GetMapping("/all")
	public Api<List<PostDto>> list(
		@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
		final Pageable pageable
	) {
		return postService.all(pageable);
	}

	@PostMapping("/delete")
	public void delete(
		@Valid
		@RequestBody
		final PostViewRequest postViewRequest
	) {
		postService.delete(postViewRequest);
	}

}

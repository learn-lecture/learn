package com.study.simpleboard.post.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.post.db.PostEntity;
import com.study.simpleboard.post.model.PostRequest;
import com.study.simpleboard.post.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@PostMapping("")
	public PostEntity create(@Valid @RequestBody final PostRequest postRequest) {
		return postService.create(postRequest);
	}

}

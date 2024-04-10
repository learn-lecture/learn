package com.study.simpleboard.reply.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.common.Api;
import com.study.simpleboard.post.model.PostViewRequest;
import com.study.simpleboard.reply.db.ReplyEntity;
import com.study.simpleboard.reply.model.ReplyDto;
import com.study.simpleboard.reply.model.ReplyRequest;
import com.study.simpleboard.reply.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyApiController {

	private final ReplyService replyService;

	@PostMapping("")
	public ReplyDto create(@Valid @RequestBody final ReplyDto request) {
		return replyService.create(request);
	}

	@GetMapping("/all")
	public Api<List<ReplyDto>> findAll(
		@PageableDefault(sort = "id", direction = Sort.Direction.DESC)
		final Pageable pageable
	) {
		return replyService.list(pageable);
	}

	@PostMapping("/delete")
	public void delete(@RequestBody final ReplyDto replyDto) {
		replyService.delete(replyDto.id());
	}

	@GetMapping("/{id}")
	public ReplyDto read(@PathVariable final Long id) {
		return replyService.read(id);
	}

}

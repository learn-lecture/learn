package com.study.simpleboard.reply.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.board.db.BoardEntity;
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
	public ReplyDto create(@Valid @RequestBody final ReplyRequest replyRequest) {
		return replyService.create(replyRequest);
	}



}

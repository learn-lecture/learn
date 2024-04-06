package com.study.simpleboard.board.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.model.BoardRequest;
import com.study.simpleboard.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

	private final BoardService boardService;

	@PostMapping("")
	public BoardEntity create(@Valid @RequestBody final BoardRequest boardRequest) {
		return boardService.create(boardRequest);
	}

}

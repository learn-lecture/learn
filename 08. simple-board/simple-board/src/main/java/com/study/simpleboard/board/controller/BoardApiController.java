package com.study.simpleboard.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.model.BoardRequest;
import com.study.simpleboard.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

	private final BoardService boardService;

	@PostMapping("")
	public BoardEntity create(@Valid @RequestBody final BoardRequest boardRequest) {
		return boardService.create(boardRequest);
	}

	@GetMapping("/{id}")
	public BoardEntity view(@PathVariable Long id) {
		final BoardEntity entity = boardService.view(id);
		log.info("result : {}", entity);

		return entity;
	}
}

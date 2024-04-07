package com.study.simpleboard.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.db.BoardRepository;
import com.study.simpleboard.board.model.BoardRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardEntity create(final BoardRequest boardRequest) {
		final BoardEntity registered = BoardEntity.builder()
			.boardName(boardRequest.boardName())
			.status("REGISTERED")
			.build();

		return boardRepository.save(registered);
	}

	public BoardEntity view(final Long id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("not found");
		});
	}

}

package com.study.simpleboard.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.study.simpleboard.board.db.BoardEntity;
import com.study.simpleboard.board.db.BoardRepository;
import com.study.simpleboard.board.model.BoardDto;
import com.study.simpleboard.board.model.BoardRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public BoardDto create(final BoardRequest boardRequest) {
	 	final BoardEntity entity = BoardEntity.builder()
			.boardName(boardRequest.boardName())
			.status("REGISTERED")
			.build();

		return BoardDto.toDto(boardRepository.save(entity));
	}

	public BoardDto view(final Long id) {
		final BoardEntity entity = boardRepository.findById(id)
			.orElseThrow(() -> {
				throw new IllegalArgumentException("not found");
			});
		return BoardDto.toDto(entity);
	}

}

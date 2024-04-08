package com.study.simpleboard.common;

import lombok.Builder;

@Builder
public record Pagination(
	Integer page,
	Integer size,
	Integer currentElements,
	Integer totalPage,
	Long totalElements
) {
}

package com.study.simpleboard.common;

import lombok.Builder;

@Builder
public record Api<T>(
	T body,
	Pagination pagination
) {
}

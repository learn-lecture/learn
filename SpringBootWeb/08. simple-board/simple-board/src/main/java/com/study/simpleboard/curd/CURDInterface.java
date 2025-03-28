package com.study.simpleboard.curd;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.study.simpleboard.common.Api;

public interface CURDInterface<T> {

	T create(T request);

	T read(Long id);

	T update(T request);

	T delete(Long id);

	Api<List<T>> list(Pageable pageable);

}

package com.study.simpleboard.curd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.study.simpleboard.common.Api;
import com.study.simpleboard.common.Pagination;

public abstract class CRUDAbstractService<T, ENTITY> implements CURDInterface<T> {

	@Autowired(required = false)
	private JpaRepository<ENTITY, Long> repository;

	@Autowired(required = false)
	private Converter<T, ENTITY> converter;

	@Override
	public T create(final T request) {
		final ENTITY entity = converter.toEntity(request, "REGISTERED");
		final ENTITY save = repository.save(entity);

		return converter.toDto(save);
	}

	@Override
	public T read(final Long id) {
		final ENTITY entity = repository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("Not Found");
		});

		return converter.toDto(entity);
	}

	@Override
	public T update(final T request) {
		final ENTITY entity = converter.toEntity(request);
		final ENTITY save = repository.save(entity);

		return converter.toDto(save);
	}

	@Override
	public T delete(final Long id) {
		final ENTITY entity = repository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("Not Found");
		});
		final ENTITY deleteEntity = converter.delete(entity);
		final ENTITY save = repository.save(deleteEntity);

		return converter.toDto(save);
	}

	@Override
	public Api<List<T>> list(final Pageable pageable) {
		final Page<ENTITY> pages = repository.findAll(pageable);

		final Pagination pagination = Pagination.builder()
			.page(pages.getNumber())
			.size(pages.getSize())
			.currentElements(pages.getNumberOfElements())
			.totalElements(pages.getTotalElements())
			.totalPage(pages.getTotalPages())
			.build();

		final List<T> dtos = pages.stream()
			.map(it -> converter.toDto(it))
			.toList();

		return Api.<List<T>>builder()
			.body(dtos)
			.pagination(pagination)
			.build();
	}

}

package com.study.simpleboard.curd;

public interface Converter<DTO, ENTITY>{

	DTO toDto(ENTITY entity);
	ENTITY toEntity(DTO dto, String status);
	ENTITY toEntity(DTO dto);
	ENTITY delete(ENTITY entity);

}

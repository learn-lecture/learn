package com.study.cookie.model;

import lombok.*;

@Builder
public record UserDto(
	String id,
	String name,
	String password
) {
}
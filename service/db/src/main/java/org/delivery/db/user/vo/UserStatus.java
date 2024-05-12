package org.delivery.db.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {

	REGISTERED("등록"),
	UNREGISTERED("해지");

	private String description;

}

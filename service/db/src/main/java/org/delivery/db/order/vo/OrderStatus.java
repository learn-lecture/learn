package org.delivery.db.order.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {

	REGISTERED("등록"),
	UNREGISTERED("해지");

	private String description;

}

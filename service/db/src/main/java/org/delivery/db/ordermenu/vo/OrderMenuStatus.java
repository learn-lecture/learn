package org.delivery.db.ordermenu.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderMenuStatus {

	REGISTERED("등록"),
	UNREGISTERED("해지");

	private String description;

}

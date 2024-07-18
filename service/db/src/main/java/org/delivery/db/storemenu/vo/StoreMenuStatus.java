package org.delivery.db.storemenu.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreMenuStatus {

	REGISTERED("등록"),
	UNREGISTERED("해지");

	private String description;

}

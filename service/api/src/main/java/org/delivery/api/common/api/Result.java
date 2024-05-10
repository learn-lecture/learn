package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Result {

	protected Integer resultCode;
	protected String resultMessage;
	protected String resultDescription;

}

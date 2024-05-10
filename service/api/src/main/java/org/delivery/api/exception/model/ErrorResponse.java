package org.delivery.api.exception.model;

import org.delivery.api.common.api.Result;

public record ErrorResponse(
	String fieldName,
	String message
) {

	@Override
	public String toString() {
		return "ErrorResponse{" +
			"fieldName='" + fieldName + '\'' +
			", message='" + message + '\'' +
			'}';
	}

}

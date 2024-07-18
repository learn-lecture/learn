package org.delivery.api.domain.store.converter;

import java.util.Optional;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.store.dto.request.StoreRegisterRequest;
import org.delivery.api.domain.store.dto.response.StoreResponse;
import org.delivery.api.domain.store.exception.StoreExceptionType;
import org.delivery.api.exception.BadRequestException;
import org.delivery.db.store.Store;

@Converter
public class StoreConverter {

	public Store toEntity(final StoreRegisterRequest request) {
		return Optional.ofNullable(request)
			.map(it -> Store.builder()
				.name(request.name())
				.address(request.address())
				.category(request.category())
				.minimumAmount(request.minimumAmount())
				.minimumDeliveryAmount(request.minimumDeliveryAmount())
				.thumbnailUrl(request.thumbnailUrl())
				.phoneNumber(request.phoneNumber())
				.build()
			).orElseThrow(() -> new BadRequestException(StoreExceptionType.NULL_POINT_EXCEPTION));
	}

	public StoreResponse toResponse(final Store entity) {
		return Optional.ofNullable(entity)
			.map(it -> new StoreResponse(
				entity.getId(),
				entity.getName(),
				entity.getAddress(),
				entity.getStatus(),
				entity.getCategory(),
				entity.getStar(),
				entity.getThumbnailUrl(),
				entity.getMinimumAmount(),
				entity.getMinimumDeliveryAmount(),
				entity.getPhoneNumber()
			)).orElseThrow(() -> new BadRequestException(StoreExceptionType.NULL_POINT_EXCEPTION));

	}

}

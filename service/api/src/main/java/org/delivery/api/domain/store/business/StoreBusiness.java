package org.delivery.api.domain.store.business;

import java.util.List;

import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.dto.request.StoreRegisterRequest;
import org.delivery.api.domain.store.dto.response.StoreResponse;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.Store;
import org.delivery.db.store.vo.StoreCategory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class StoreBusiness {

	private final StoreService storeService;
	private final StoreConverter storeConverter;

	public StoreResponse register(final StoreRegisterRequest request) {
		final Store entity = storeConverter.toEntity(request);
		final Store register = storeService.register(entity);
		return storeConverter.toResponse(register);
	}

	public List<StoreResponse> searchCategory(final StoreCategory storeCategory) {
		final List<Store> stores = storeService.searchByCategory(storeCategory);

		return stores.stream()
			.map(storeConverter::toResponse)
			.toList();
	}

}

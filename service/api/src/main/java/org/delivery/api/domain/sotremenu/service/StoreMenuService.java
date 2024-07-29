package org.delivery.api.domain.sotremenu.service;

import java.util.List;
import java.util.Optional;

import org.delivery.api.domain.sotremenu.exception.StoreMenuExceptionType;
import org.delivery.api.exception.BadRequestException;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.vo.StoreMenuStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

	private final StoreMenuRepository storeMenuRepository;

	public StoreMenu getStoreMenuWithThrow(final Long id) {
		return storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED)
			.orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.INVALID_MENU_EXCEPTION));
	}

	public List<StoreMenu> getStoreMenuByStoreId(final Long storeId) {
		return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERED);
	}

	public StoreMenu register(final StoreMenu menu) {
		return Optional.ofNullable(menu)
			.map(it -> {
				it.setStatus(StoreMenuStatus.REGISTERED);
				return storeMenuRepository.save(it);
			})
			.orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.NULL_POINT_EXCEPTION));
	}

}

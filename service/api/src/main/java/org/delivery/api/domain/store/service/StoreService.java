package org.delivery.api.domain.store.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.store.exception.StoreExceptionType;
import org.delivery.common.exception.model.BadRequestException;
import org.delivery.db.store.Store;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.vo.StoreCategory;
import org.delivery.db.store.vo.StoreStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public Store getStoreWithTrhow(final Long id) {
        return Optional.ofNullable(storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED))
            .orElseThrow(() -> new BadRequestException(StoreExceptionType.NOT_FOUND_EXCEPTION));
    }

    public Store register(final Store store) {
        return Optional.ofNullable(store)
            .map(it -> {
                it.setStar(0);
                it.setStatus(StoreStatus.REGISTERED);

                return storeRepository.save(it);
            })
            .orElseThrow(() -> new BadRequestException(StoreExceptionType.NULL_POINT_EXCEPTION));
    }

    public List<Store> searchByCategory(final StoreCategory category) {
        final List<Store> stores = storeRepository.findAllByStatusAndCategoryOrderByStarDesc(
            StoreStatus.REGISTERED, category
        );

        return stores;
    }

    public List<Store> registeredStore() {
        final List<Store> stores = storeRepository.findAllByStatusOrderByIdDesc(
            StoreStatus.REGISTERED);
        return stores;
    }

}

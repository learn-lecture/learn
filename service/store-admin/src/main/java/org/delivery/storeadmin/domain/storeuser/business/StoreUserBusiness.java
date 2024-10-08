package org.delivery.storeadmin.domain.storeuser.business;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.Store;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.vo.StoreStatus;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.domain.storeuser.converter.StoreUserConverter;
import org.delivery.storeadmin.domain.storeuser.dto.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.storeuser.dto.StoreUserResponse;
import org.delivery.storeadmin.domain.storeuser.service.StoreUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserBusiness {

    private final StoreUserConverter storeUserConverter;
    private final StoreUserService storeUserService;
    private final StoreRepository storeRepository;

    public StoreUserResponse register(
        StoreUserRegisterRequest request
    ) {
        String storeName = request.storeName();
        Store store = storeRepository.findFirstByNameAndStatusOrderByIdDesc(storeName, StoreStatus.REGISTERED)
            .orElseThrow(IllegalAccessError::new);

        StoreUser storeUser = storeUserConverter.toEntity(request, store);
        StoreUser registeredUser = storeUserService.register(storeUser);
        return storeUserConverter.toResponse(registeredUser, store);
    }

}

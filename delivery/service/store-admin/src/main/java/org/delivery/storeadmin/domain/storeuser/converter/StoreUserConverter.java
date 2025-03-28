package org.delivery.storeadmin.domain.storeuser.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.db.store.Store;
import org.delivery.db.storeuser.StoreUser;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.storeuser.dto.StoreResponse;
import org.delivery.storeadmin.domain.storeuser.dto.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.storeuser.dto.StoreUserResponse;
import org.delivery.storeadmin.domain.storeuser.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreUserConverter {

    public StoreUser toEntity(StoreUserRegisterRequest request, Store store) {
        return StoreUser.builder()
            .email(request.email())
            .password(request.password())
            .role(request.role())
            .storeId(store.getId())
            .build();
    }

    public StoreUserResponse toResponse(StoreUser storeUser, Store store) {
        UserResponse userResponse = new UserResponse(
            storeUser.getStoreId(),
            storeUser.getEmail(),
            storeUser.getStatus(),
            storeUser.getRole(),
            storeUser.getRegisteredAt(),
            storeUser.getUnregisteredAt(),
            storeUser.getLastLoginAt()
        );
        StoreResponse storeResponse = new StoreResponse(store.getId(), store.getName());

        return new StoreUserResponse(userResponse, storeResponse);
    }

    public StoreUserResponse toResponse(UserSession userSession) {
        UserResponse userResponse = new UserResponse(
            userSession.getStoreId(),
            userSession.getEmail(),
            userSession.getStatus(),
            userSession.getRole(),
            userSession.getRegisteredAt(),
            userSession.getUnregisteredAt(),
            userSession.getLastLoginAt()
        );
        StoreResponse storeResponse = new StoreResponse(userSession.getStoreId(), userSession.getStoreName());

        return new StoreUserResponse(userResponse, storeResponse);
    }

}

package org.delivery.storeadmin.domain.storeuser.dto;

public record StoreUserResponse(
    UserResponse user,
    StoreResponse store
) {

}

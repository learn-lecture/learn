package org.delivery.storeadmin.domain.user.dto;

public record StoreUserResponse(
    UserResponse user,
    StoreResponse store
) {

}

package org.delivery.storeadmin.domain.user.dto;


import java.time.LocalDateTime;
import org.delivery.db.storeuser.vo.StoreUserRole;
import org.delivery.db.storeuser.vo.StoreUserStatus;

public record UserResponse(
    Long storeId,
    String email,
    StoreUserStatus status,
    StoreUserRole role,
    LocalDateTime registeredAt,
    LocalDateTime unregisteredAt,
    LocalDateTime lastLoginAt
) {

}
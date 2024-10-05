package org.delivery.storeadmin.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import org.delivery.db.storeuser.vo.StoreUserRole;

public record StoreUserRegisterRequest(
    @NotBlank String storeName,
    @NotBlank String email,
    @NotBlank String password,
    @NotBlank StoreUserRole role
) {
}

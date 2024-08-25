package org.delivery.api.domain.sotremenu.converter;

import java.util.List;
import java.util.Optional;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.domain.sotremenu.dto.request.StoreMenuRegisterRequest;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.sotremenu.exception.StoreMenuExceptionType;
import org.delivery.api.exception.BadRequestException;
import org.delivery.db.storemenu.StoreMenu;

@Converter
public class StoreMenuConverter {

    public StoreMenu toEntity(final StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> StoreMenu.builder()
                        .storeId(request.storeId())
                        .name(request.name())
                        .amount(request.amount())
                        .thumbnailUrl(request.thumbnailUrl())
                        .build()
                ).orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.NULL_POINT_EXCEPTION));
    }

    public StoreMenuResponse toResponse(final StoreMenu storeMenu) {
        return Optional.ofNullable(storeMenu)
                .map(it -> new StoreMenuResponse(
                        storeMenu.getId(),
                        storeMenu.getStoreId(),
                        storeMenu.getName(),
                        storeMenu.getAmount(),
                        storeMenu.getStatus(),
                        storeMenu.getThumbnailUrl(),
                        storeMenu.getLikeCount(),
                        storeMenu.getSequence()
                )).orElseThrow(() -> new BadRequestException(StoreMenuExceptionType.NULL_POINT_EXCEPTION));
    }

    public List<StoreMenuResponse> toResponse(final List<StoreMenu> storeMenus) {
        return storeMenus.stream()
                .map(this::toResponse)
                .toList();
    }

}

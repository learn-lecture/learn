package org.delivery.storeadmin.domain.storemenu.converter;

import java.util.List;
import org.delivery.db.storemenu.StoreMenu;
import org.delivery.storeadmin.domain.storemenu.dto.StoreMenuResponse;
import org.springframework.stereotype.Service;

@Service
public class StoreMenuConverter {

    public StoreMenuResponse toResponse(StoreMenu menu) {
        return StoreMenuResponse.builder()
            .id(menu.getId())
            .name(menu.getName())
            .amount(menu.getAmount())
            .status(menu.getStatus())
            .thumbnailUrl(menu.getThumbnailUrl())
            .likeCount(menu.getLikeCount())
            .sequence(menu.getSequence())
            .build();
    }

    public List<StoreMenuResponse> toResponse(List<StoreMenu> menus) {
        return menus.stream()
            .map(this::toResponse)
            .toList();
    }

}

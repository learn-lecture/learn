package org.delivery.api.domain.sotremenu.business;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.sotremenu.converter.StoreMenuConverter;
import org.delivery.api.domain.sotremenu.dto.request.StoreMenuRegisterRequest;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.sotremenu.service.StoreMenuService;
import org.delivery.common.annotation.Business;
import org.delivery.db.storemenu.StoreMenu;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(final StoreMenuRegisterRequest request) {
        final StoreMenu store = storeMenuConverter.toEntity(request);
        final StoreMenu register = storeMenuService.register(store);
        return storeMenuConverter.toResponse(register);
    }

    public List<StoreMenuResponse> search(final Long storeId) {
        return storeMenuService.getStoreMenuByStoreId(storeId).stream()
            .map(storeMenuConverter::toResponse)
            .toList();
    }

}

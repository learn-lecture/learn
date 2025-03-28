package org.delivery.api.domain.userorder.dto.resp;

import java.util.List;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.store.dto.response.StoreResponse;

public record UserOrderDetailResponse(
        UserOrderResponse userOrderResponse,
        StoreResponse storeResponse,
        List<StoreMenuResponse> storeMenusResponse
) {

    public static UserOrderDetailResponse of(
            final UserOrderResponse userOrderResponse,
            final StoreResponse storeResponse,
            final List<StoreMenuResponse> storeMenusResponse
    ) {
        return new UserOrderDetailResponse(userOrderResponse, storeResponse, storeMenusResponse);
    }

}

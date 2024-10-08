package org.delivery.storeadmin.domain.userorder.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.delivery.storeadmin.domain.storemenu.dto.StoreMenuResponse;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class UserOrderDetailResponse {

    private UserOrderResponse userOrder;
    private List<StoreMenuResponse> storeMenus;

}

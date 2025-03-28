package org.delivery.api.domain.sotremenu.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.sotremenu.business.StoreMenuBusiness;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.sotremenu.dto.response.info.StoreMenuResponseStatus;
import org.delivery.common.api.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store-menu")
public class StoreMenuApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @GetMapping("/search")
    public ResponseEntity<Api<List<StoreMenuResponse>>> search(@RequestParam Long storeId) {
        final List<StoreMenuResponse> response = storeMenuBusiness.search(storeId);
        return ResponseEntity.ok(Api.ok(StoreMenuResponseStatus.SEARCH_STORE_SUCCESS, response));
    }

}

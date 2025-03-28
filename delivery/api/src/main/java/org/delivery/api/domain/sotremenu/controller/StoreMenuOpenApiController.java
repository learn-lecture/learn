package org.delivery.api.domain.sotremenu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.sotremenu.business.StoreMenuBusiness;
import org.delivery.api.domain.sotremenu.dto.request.StoreMenuRegisterRequest;
import org.delivery.api.domain.sotremenu.dto.response.StoreMenuResponse;
import org.delivery.api.domain.sotremenu.dto.response.info.StoreMenuResponseStatus;
import org.delivery.common.api.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {

    private final StoreMenuBusiness storeMenuBusiness;

    @PostMapping("/register")
    public ResponseEntity<Api<StoreMenuResponse>> register(
        @Valid @RequestBody final StoreMenuRegisterRequest request
    ) {
        final StoreMenuResponse response = storeMenuBusiness.register(request);
        return ResponseEntity.ok(Api.ok(StoreMenuResponseStatus.CREATE_MENU_SUCCESS, response));
    }
}

package org.delivery.api.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.dto.request.StoreRegisterRequest;
import org.delivery.api.domain.store.dto.response.StoreResponse;
import org.delivery.api.domain.store.dto.response.info.StoreResponseStatus;
import org.delivery.common.api.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
public class StoreOpenApiController {

    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public ResponseEntity<Api<StoreResponse>> register(
        @Valid @RequestBody final StoreRegisterRequest request
    ) {
        final StoreResponse response = storeBusiness.register(request);
        return ResponseEntity.ok(Api.ok(StoreResponseStatus.CREATE_STORE_SUCCESS, response));
    }

}

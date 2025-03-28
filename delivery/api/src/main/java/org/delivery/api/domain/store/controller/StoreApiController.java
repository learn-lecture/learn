package org.delivery.api.domain.store.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.domain.store.business.StoreBusiness;
import org.delivery.api.domain.store.dto.response.StoreResponse;
import org.delivery.api.domain.store.dto.response.info.StoreResponseStatus;
import org.delivery.common.api.Api;
import org.delivery.db.store.vo.StoreCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreApiController {

    private final StoreBusiness storeBusiness;

    @GetMapping("/search")
    public ResponseEntity<Api<List<StoreResponse>>> search(
        @RequestParam(required = false) final StoreCategory storeCategory
    ) {
        final List<StoreResponse> responses = storeBusiness.searchCategory(storeCategory);
        return ResponseEntity.ok(Api.ok(StoreResponseStatus.SEARCH_STORE_SUCCESS, responses));
    }

}

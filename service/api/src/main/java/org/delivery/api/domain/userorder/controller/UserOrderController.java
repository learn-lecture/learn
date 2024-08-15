package org.delivery.api.domain.userorder.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.dto.req.UserOrderRequest;
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse;
import org.delivery.api.domain.userorder.dto.resp.info.UserOrderResponseStatus;
import org.delivery.db.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class UserOrderController {

    private final UserOrderBusiness userOrderBusiness;

    @PostMapping
    public ResponseEntity<Api<UserOrderResponse>> userOrder(
            @RequestBody final UserOrderRequest request,
            @UserSession final User user
    ) {
        return ResponseEntity.ok(Api.ok(
                UserOrderResponseStatus.USER_ORDER_SUCCESS,
                userOrderBusiness.userOrder(request, user)
        ));
    }
}

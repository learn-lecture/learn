package org.delivery.api.domain.userorder.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.dto.req.UserOrderRequest;
import org.delivery.api.domain.userorder.dto.resp.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.dto.resp.UserOrderResponse;
import org.delivery.api.domain.userorder.dto.resp.info.UserOrderResponseStatus;
import org.delivery.db.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/current")
    public ResponseEntity<Api<List<UserOrderDetailResponse>>> current(
            @UserSession final User user
    ) {
        final List<UserOrderDetailResponse> response = userOrderBusiness.current(user);
        return ResponseEntity.ok(Api.ok(UserOrderResponseStatus.GET_CURRENT_SUCCESS, response));
    }

    @GetMapping("/history")
    public ResponseEntity<Api<List<UserOrderDetailResponse>>> history(
            @UserSession final User user
    ) {
        final List<UserOrderDetailResponse> response = userOrderBusiness.history(user);
        return ResponseEntity.ok(Api.ok(UserOrderResponseStatus.GET_HISTORY_SUCCESS, response));
    }

    @GetMapping("/id/{orderId}")
    public ResponseEntity<Api<UserOrderDetailResponse>> read(
            @UserSession final User user,
            @PathVariable final Long orderId
    ) {
        final UserOrderDetailResponse response = userOrderBusiness.read(user, orderId);
        return ResponseEntity.ok(Api.ok(UserOrderResponseStatus.GET_CURRENT_READ_SUCCESS, response));
    }

}

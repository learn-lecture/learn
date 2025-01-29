package org.demo.jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.demo.jpashop.domain.Address;
import org.demo.jpashop.domain.Order;
import org.demo.jpashop.domain.OrderItem;
import org.demo.jpashop.domain.OrderStatus;
import org.demo.jpashop.repository.OrderRepository;
import org.demo.jpashop.repository.OrderSearch;
import org.demo.jpashop.repository.order.query.OrderFlatDto;
import org.demo.jpashop.repository.order.query.OrderItemQueryDto;
import org.demo.jpashop.repository.order.query.OrderQueryDto;
import org.demo.jpashop.repository.order.query.OrderQueryRepository;
import org.demo.jpashop.service.query.OrderDto;
import org.demo.jpashop.service.query.OrderQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderApIController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;
    private final OrderQueryService orderQueryService;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        return orderQueryService.ordersV1();
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderQueryService.ordersV2();
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderQueryService.ordersV3();
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit
    ) {
        return orderQueryService.ordersV3_page(offset, limit);
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> ordersV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
        return flats.stream()
                .collect(
                        Collectors.groupingBy(
                                OrderQueryDto::new,
                                Collectors.mapping(OrderItemQueryDto::new, Collectors.toList())
                        )
                )
                .entrySet()
                .stream()
                .map(e -> new OrderQueryDto(e.getKey(), e.getValue()))
                .toList();
    }




}

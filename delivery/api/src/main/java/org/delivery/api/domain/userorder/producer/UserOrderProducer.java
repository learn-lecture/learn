package org.delivery.api.domain.userorder.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.order.UserOrder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

    private static final String EXCHANGE = "delivery.exchange";
    private static final String ROUTE_KEY = "delivery.key";
    private final Producer producer;

    public void sendOrder(UserOrder userOrder) {
        sendOrder(userOrder.getId());
    }

    public void sendOrder(Long userOrderId) {
        UserOrderMessage userOrderMessage = UserOrderMessage.builder()
            .userOrderId(userOrderId)
            .build();

        producer.producer(EXCHANGE, ROUTE_KEY, userOrderMessage);
    }

}

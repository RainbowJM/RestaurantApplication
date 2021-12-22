package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}

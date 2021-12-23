package com.restaurant.OrderService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrderRemovedEvent extends OrderEvent {
    public static final String KEY = "order.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String orderId;
}

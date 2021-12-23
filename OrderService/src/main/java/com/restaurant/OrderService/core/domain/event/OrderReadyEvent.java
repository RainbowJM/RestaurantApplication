package com.restaurant.OrderService.core.domain.event;

public class OrderReadyEvent extends OrderEvent {
    public static final String KEY = "order.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

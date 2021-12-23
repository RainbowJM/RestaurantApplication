package com.restaurant.OrderService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OrderChangedStatusEvent extends OrderEvent {
    public static final String KEY = "order.event.changed-status";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String orderId;

    @Getter
    private final String status;
}

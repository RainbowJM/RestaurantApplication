package com.restaurant.UserService.adapter.ingoing.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(OrderCreatedEvent.KEY)
public class OrderCreatedEvent extends OrderEvent {
    public static final String KEY = "order.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String orderId;

    @Getter
    private final String restaurantId;

    @Getter
    private final String customerId;

    @Getter
    private final String status;

    @Getter
    private final String deliveryAddress;
}

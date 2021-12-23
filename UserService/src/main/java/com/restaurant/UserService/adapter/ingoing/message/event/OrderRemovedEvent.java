package com.restaurant.UserService.adapter.ingoing.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(OrderRemovedEvent.KEY)
public class OrderRemovedEvent extends OrderEvent {
    public static final String KEY = "order.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String orderId;
}

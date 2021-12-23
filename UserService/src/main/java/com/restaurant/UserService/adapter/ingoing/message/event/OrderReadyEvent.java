package com.restaurant.UserService.adapter.ingoing.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(OrderReadyEvent.KEY)
public class OrderReadyEvent extends OrderEvent {
    public static final String KEY = "order.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

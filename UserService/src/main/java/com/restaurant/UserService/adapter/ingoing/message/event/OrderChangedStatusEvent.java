package com.restaurant.UserService.adapter.ingoing.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(OrderChangedStatusEvent.KEY)
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

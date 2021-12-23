package com.restaurant.OrderService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonTypeName(UserReadyEvent.KEY)
public class UserReadyEvent extends UserEvent {
    public static final String KEY = "user.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

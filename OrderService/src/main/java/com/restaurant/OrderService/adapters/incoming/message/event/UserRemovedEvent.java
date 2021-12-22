package com.restaurant.OrderService.adapters.incoming.message.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserRemovedEvent extends UserEvent {
    public static final String KEY = "user.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String username;
}

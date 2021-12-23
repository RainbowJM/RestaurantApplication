package com.restaurant.UserService.core.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserReadyEvent extends UserEvent {
    public static final String KEY = "user.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

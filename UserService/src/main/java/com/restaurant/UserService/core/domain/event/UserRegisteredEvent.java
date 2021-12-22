package com.restaurant.UserService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserRegisteredEvent extends UserEvent {
    public static final String KEY = "user.event.registered";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String username;

    @Getter
    private final String role;
}

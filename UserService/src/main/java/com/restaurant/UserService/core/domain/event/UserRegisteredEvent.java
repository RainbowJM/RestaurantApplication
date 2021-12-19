package com.restaurant.UserService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserRegisteredEvent extends UserEvent {
    @Getter
    private final String userId;
    @Getter
    private final String username;

    @Override
    public String getEventKey() {
        return "user.registered";
    }
}

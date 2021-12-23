package com.restaurant.OrderService.adapters.incoming.message.event.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(UserRegisteredEvent.KEY)
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

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;
}

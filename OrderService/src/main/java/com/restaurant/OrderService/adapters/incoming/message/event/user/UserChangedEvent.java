package com.restaurant.OrderService.adapters.incoming.message.event.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(UserChangedEvent.KEY)
public class UserChangedEvent extends UserEvent {
    public static final String KEY = "user.event.changed";

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

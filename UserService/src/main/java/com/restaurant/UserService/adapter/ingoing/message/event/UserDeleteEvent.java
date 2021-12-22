package com.restaurant.UserService.adapter.ingoing.message.event;

import lombok.Getter;

public class UserDeleteEvent extends UserEvent {
    public static final String KEY = "user.commands.delete";

    @Getter
    private final String username;

    public UserDeleteEvent(String username) {
        this.username = username;
    }

    @Override
    public String getEventKey() {
        return KEY;
    }
}

package com.restaurant.UserService.adapter.ingoing.message;

import com.restaurant.UserService.core.application.UserCommandService;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {
    private final UserCommandService commandService;

    public UserEventListener(UserCommandService commandService) {
        this.commandService = commandService;
    }
}

package com.restaurant.UserService.core.port;

import com.restaurant.UserService.core.domain.event.UserEvent;

public interface UserEventPublisher {
    void publish(UserEvent event);
}

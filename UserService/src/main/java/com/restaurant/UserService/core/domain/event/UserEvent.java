package com.restaurant.UserService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public abstract class UserEvent {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}

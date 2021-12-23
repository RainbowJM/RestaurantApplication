package com.restaurant.TableService.core.domain.event;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

public abstract class TableEvent {
    @Getter
    private final UUID id = UUID.randomUUID();

    @Getter
    private  final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}

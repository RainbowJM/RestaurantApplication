package com.restaurant.OrderService.adapters.incoming.message.event.menu;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "eventKey"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MenuReadyEvent.class),
        @JsonSubTypes.Type(value = MenuCreatedEvent.class),
        @JsonSubTypes.Type(value = MenuChangedEvent.class),
        @JsonSubTypes.Type(value = MenuRemovedEvent.class),
})
public abstract class MenuEvent {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}

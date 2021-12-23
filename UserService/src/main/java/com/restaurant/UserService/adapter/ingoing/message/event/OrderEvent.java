package com.restaurant.UserService.adapter.ingoing.message.event;

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
        @JsonSubTypes.Type(value = OrderReadyEvent.class),
        @JsonSubTypes.Type(value = OrderCreatedEvent.class),
        @JsonSubTypes.Type(value = OrderRemovedEvent.class),
})
public abstract class OrderEvent {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}

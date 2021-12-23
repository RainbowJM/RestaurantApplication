package com.restaurant.OrderService.adapters.incoming.message.event.restaurant;

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
        @JsonSubTypes.Type(value = RestaurantReadyEvent.class),
        @JsonSubTypes.Type(value = RestaurantCreatedEvent.class),
        @JsonSubTypes.Type(value = RestaurantRemovedEvent.class),
})
public abstract class RestaurantEvent {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private final Instant timestamp = Instant.now();

    public abstract String getEventKey();
}

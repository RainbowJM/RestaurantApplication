package com.restaurant.OrderService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(RestaurantRemovedEvent.KEY)
public class RestaurantRemovedEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String name;
}

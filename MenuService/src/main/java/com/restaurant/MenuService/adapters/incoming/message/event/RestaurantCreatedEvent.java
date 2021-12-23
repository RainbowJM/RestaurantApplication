package com.restaurant.MenuService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonTypeName(RestaurantCreatedEvent.KEY)
public class RestaurantCreatedEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.created";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String name;

    @Getter
    private final String address;
}

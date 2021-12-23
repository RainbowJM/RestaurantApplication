package com.restaurant.RestaurantService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
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

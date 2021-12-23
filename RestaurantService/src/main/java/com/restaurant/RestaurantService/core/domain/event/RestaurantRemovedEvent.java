package com.restaurant.RestaurantService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RestaurantRemovedEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.removed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String name1;
}

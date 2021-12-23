package com.restaurant.RestaurantService.core.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantReadyEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

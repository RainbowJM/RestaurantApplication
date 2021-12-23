package com.restaurant.RestaurantService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RestaurantAddressChangedEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.address-changed";

    @Override
    public String getEventKey() {
        return KEY;
    }

    @Getter
    private final String name;

    @Getter
    private final String address;
}

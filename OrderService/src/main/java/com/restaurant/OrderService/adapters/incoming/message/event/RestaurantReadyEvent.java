package com.restaurant.OrderService.adapters.incoming.message.event;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;

@JsonTypeName(RestaurantReadyEvent.KEY)
public class RestaurantReadyEvent extends RestaurantEvent {
    public static final String KEY = "restaurant.event.ready";

    @Override
    public String getEventKey() {
        return KEY;
    }
}

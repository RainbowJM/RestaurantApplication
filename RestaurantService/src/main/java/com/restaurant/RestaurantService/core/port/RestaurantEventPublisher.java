package com.restaurant.RestaurantService.core.port;

import com.restaurant.RestaurantService.core.domain.event.RestaurantEvent;

public interface RestaurantEventPublisher {
    void publish(RestaurantEvent event);
}

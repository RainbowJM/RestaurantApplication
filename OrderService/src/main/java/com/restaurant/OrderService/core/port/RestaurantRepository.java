package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.external.Restaurant;
import com.restaurant.OrderService.core.domain.external.User;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> getAllRestaurants();
}

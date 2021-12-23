package com.restaurant.MenuService.core.port;

import com.restaurant.MenuService.core.domain.external.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> getAllRestaurants();
}

package com.restaurant.TableService.core.port;

import com.restaurant.TableService.core.domain.external.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> getRestaurantFromTable(String id);
}

package com.restaurant.TableService.core.port;

import com.restaurant.TableService.adapter.outgoing.rest.RestaurantResult;

import java.util.List;

public interface RestaurantRepository {
    List<RestaurantResult> getRestaurantFromTable(String id);
}

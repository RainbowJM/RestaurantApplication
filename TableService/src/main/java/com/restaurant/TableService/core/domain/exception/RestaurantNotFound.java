package com.restaurant.TableService.core.domain.exception;

public class RestaurantNotFound extends RuntimeException{
    public RestaurantNotFound(String id) {
        super(String.format("Table not found %s", id));
    }
}

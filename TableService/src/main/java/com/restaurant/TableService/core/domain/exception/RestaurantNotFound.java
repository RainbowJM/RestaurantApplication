package com.restaurant.TableService.core.domain.exception;

public class RestaurantNotFound extends RuntimeException{
    public RestaurantNotFound(String id) {
        super(String.format("Can't create table at unknown restaurant '%s'", id));
    }
}

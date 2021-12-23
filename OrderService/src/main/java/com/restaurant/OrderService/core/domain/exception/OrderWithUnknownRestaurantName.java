package com.restaurant.OrderService.core.domain.exception;

public class OrderWithUnknownRestaurantName extends RuntimeException {
    public OrderWithUnknownRestaurantName() {
        super("Can't create order with an invalid or unknown restaurant name!");
    }
}

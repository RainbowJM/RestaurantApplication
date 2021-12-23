package com.restaurant.OrderService.core.domain.exception;

public class OrderWithUnknownUsername extends RuntimeException {
    public OrderWithUnknownUsername() {
        super("Couldn't create an order with that unknown username.");
    }
}

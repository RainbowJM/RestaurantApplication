package com.restaurant.OrderService.core.domain.exception;

public class OrderWithUnknownDishes extends RuntimeException{
    public OrderWithUnknownDishes() {
        super("Can't create order with an invalid or unknown dish id!");
    }
}

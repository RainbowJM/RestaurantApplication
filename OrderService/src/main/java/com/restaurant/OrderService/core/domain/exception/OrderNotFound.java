package com.restaurant.OrderService.core.domain.exception;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(String orderId){
        super(String.format("Order with id %s does not exist.", orderId));
    }
}
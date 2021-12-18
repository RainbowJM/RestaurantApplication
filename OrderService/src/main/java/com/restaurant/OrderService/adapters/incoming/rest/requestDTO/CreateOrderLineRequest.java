package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import lombok.Getter;

@Getter
public class CreateOrderLineRequest {
    String productId;
    int amount;
    float price;
}
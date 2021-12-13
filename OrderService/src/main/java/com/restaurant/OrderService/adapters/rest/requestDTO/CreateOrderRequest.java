package com.restaurant.OrderService.adapters.rest.requestDTO;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateOrderRequest {
    Long customerId;
    String orderDate;
    String status;
    String deliverAddress;
    float totalPrice;
}

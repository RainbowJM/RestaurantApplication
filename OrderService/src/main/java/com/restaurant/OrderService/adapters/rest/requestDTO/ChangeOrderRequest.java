package com.restaurant.OrderService.adapters.rest.requestDTO;

import lombok.Getter;

import java.util.Date;

@Getter
public class ChangeOrderRequest {
    Long orderId;
    Long customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;
}

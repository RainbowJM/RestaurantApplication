package com.restaurant.OrderService.adapters.rest.requestDTO;

import lombok.Getter;

import java.util.Date;

@Getter
public class ChangeOrderRequest {
    String orderId;
    String customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;
}

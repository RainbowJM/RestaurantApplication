package com.restaurant.OrderService.data;

import java.util.Date;

public class OrderDto {
    Long orderId;
    Long customerId;
    Date orderDate;
    String status;
    String deliveryAdress;
    float totalPrice;
}

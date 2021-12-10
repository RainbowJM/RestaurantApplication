package com.restaurant.OrderService.domain;

import java.util.Date;

public class Order {
    Long orderId;
    Long customerId;
    Date orderDate;
    String status;
    String deliverAdress;
    float totalPrice;

    public Order(){ }

    public Order(Long orderId, Long customerId, Date orderDate, String status, String deliverAdress, float totalPrice){
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.deliverAdress = deliverAdress;
        this.totalPrice = totalPrice;
    }
}

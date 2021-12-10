package com.restaurant.OrderService.domain;

public class OrderLine {
    Long productId;
    int amount;
    float price;

    public OrderLine(Long productId, int amount, float price){
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }
}

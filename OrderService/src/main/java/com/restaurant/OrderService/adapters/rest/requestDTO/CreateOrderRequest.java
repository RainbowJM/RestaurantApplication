package com.restaurant.OrderService.adapters.rest.requestDTO;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class CreateOrderRequest {
    String customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public CreateOrderRequest(String customerId, String orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
        this.status = status;
        this.deliverAddress = deliverAddress;
        this.totalPrice = totalPrice;

        try {
            this.orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

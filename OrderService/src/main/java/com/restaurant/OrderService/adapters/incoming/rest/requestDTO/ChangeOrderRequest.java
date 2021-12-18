package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class ChangeOrderRequest {
    String orderId;
    String restaurantId;
    String customerId;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public ChangeOrderRequest(String customerId, String restaurantId, String orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
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

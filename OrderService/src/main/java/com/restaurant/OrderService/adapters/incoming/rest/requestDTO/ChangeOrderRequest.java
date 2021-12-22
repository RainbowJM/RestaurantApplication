package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import com.restaurant.OrderService.core.domain.OrderLine;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class ChangeOrderRequest {
    String orderId;
    String restaurantId;
    String customerId;
    List<CreateOrderLineRequest> lines;
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;

    public ChangeOrderRequest(String customerId, String restaurantId, List<CreateOrderLineRequest> lines, String orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.lines = lines;
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
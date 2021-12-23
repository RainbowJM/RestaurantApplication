package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.OrderStatus;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class CreateOrderRequest {
    String customerId;
    String restaurantId;
    List<CreateOrderLineRequest> orderLines;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    Date orderDate;
    OrderStatus status;
    String deliverAddress;
    float totalPrice;

    public CreateOrderRequest(String customerId, String restaurantId, List<CreateOrderLineRequest> lines, String orderDate, String status, String deliverAddress, float totalPrice){
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderLines = lines;
        this.deliverAddress = deliverAddress;
        this.totalPrice = totalPrice;

        this.status = OrderStatus.valueOf(status);

        try {
            this.orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

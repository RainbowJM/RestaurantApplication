package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import com.restaurant.OrderService.core.domain.OrderLine;
import com.restaurant.OrderService.core.domain.OrderStatus;
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
    OrderStatus status;
    String deliverAddress;

    public ChangeOrderRequest(String orderId, String restaurantId, String customerId, List<CreateOrderLineRequest> lines, String status, String deliverAddress){
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        System.out.println(lines);
        this.lines = lines;
        this.status = OrderStatus.valueOf(status);
        this.deliverAddress = deliverAddress;

//        try {
//            this.orderDate = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}

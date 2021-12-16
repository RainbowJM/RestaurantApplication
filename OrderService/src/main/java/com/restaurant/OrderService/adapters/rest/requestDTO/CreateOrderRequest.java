package com.restaurant.OrderService.adapters.rest.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

@Getter
public class CreateOrderRequest {
    String customerId;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    Date orderDate;
    String status;
    String deliverAddress;
    float totalPrice;
}

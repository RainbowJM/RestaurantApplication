package com.restaurant.OrderService.core.application.command;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;

import java.util.Date;
import java.util.List;

public record CreateOrderCommand (String customerId, String restaurantId, List<CreateOrderLineRequest> lines, Date orderdate, String status, String deliverAddress, float totalPrice) {
}

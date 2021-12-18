package com.restaurant.OrderService.core.application.command;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;

import java.util.Date;
import java.util.List;

public record ChangeOrderCommand (String orderId, String customerId, String restaurantId, List<CreateOrderLineRequest> lines, Date orderDate, String status, String deliverAddress, float totalPrice) {
}

package com.restaurant.OrderService.core.application.command;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.domain.OrderStatus;

import java.util.Date;
import java.util.List;

public record CreateOrderCommand (String customerId, String restaurantId, List<CreateOrderLineRequest> lines, Date orderdate, OrderStatus status, String deliverAddress, float totalPrice) {
}

package com.restaurant.OrderService.core.application.command;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.domain.OrderStatus;

import java.util.Date;
import java.util.List;

public record ChangeOrderCommand (String orderId, String restaurantId, String customerId, List<CreateOrderLineRequest> lines, OrderStatus status, String deliverAddress) {
}

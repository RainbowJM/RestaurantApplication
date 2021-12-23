package com.restaurant.OrderService.core.application.command;

import com.restaurant.OrderService.adapters.incoming.rest.requestDTO.CreateOrderLineRequest;
import com.restaurant.OrderService.core.domain.OrderStatus;
import com.restaurant.OrderService.core.domain.OrderType;

import java.util.Date;
import java.util.List;

public record CreateOrderCommand (String customerId, String restaurantId, OrderType orderType, List<CreateOrderLineRequest> lines, Date orderdate, OrderStatus status, String location) {
}

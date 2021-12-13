package com.restaurant.OrderService.core.application.command;

import java.util.Date;

public record CreateOrderCommand (Long customerId, Date orderdate, String status, String deliverAddress, float totalPrice) {
}

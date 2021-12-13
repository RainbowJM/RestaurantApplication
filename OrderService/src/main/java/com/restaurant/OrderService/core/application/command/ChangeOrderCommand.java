package com.restaurant.OrderService.core.application.command;

import java.util.Date;

public record ChangeOrderCommand (String orderId, String customerId, Date orderdate, String status, String deliverAddress, float totalPrice) {
}

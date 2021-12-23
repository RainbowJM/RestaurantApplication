package com.restaurant.OrderService.core.application.command;

public record UpdateOrderStatus(String orderId, String orderStatus) {
}

package com.restaurant.OrderService.core.domain;

public enum OrderStatus {
    CREATED,
    APPROVED,
    ON_HOLD,
    PREPARING_ORDER,
    ORDER_DELIVERED,
    CANCELLED
}

package com.restaurant.UserService.core.domain.external;

public record Order(String id, String restaurantId, String customerId, String status, String deliveryAddress) {
}


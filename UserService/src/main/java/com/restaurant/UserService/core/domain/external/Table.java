package com.restaurant.UserService.core.domain.external;

public record Table(String id, String restaurantId, Long numberOfSeats, String user) {
}

package com.restaurant.UserService.adapter.outgoing.message;

public record TableResult(String id, String restaurantId, Long numberOfSeats, String user) {
}

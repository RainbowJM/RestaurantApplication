package com.restaurant.TableService.core.application.command;

public record AddTableCommand(String restaurantId, String location, Long numberOfSeats) {
}

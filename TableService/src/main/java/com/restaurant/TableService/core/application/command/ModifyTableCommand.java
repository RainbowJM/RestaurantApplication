package com.restaurant.TableService.core.application.command;

public record ModifyTableCommand (String restaurantId, String tableId, String location, Long numberOfSeats){
}

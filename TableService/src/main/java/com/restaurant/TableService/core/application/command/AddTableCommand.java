package com.restaurant.TableService.core.application.command;

public record AddTableCommand(String id, long numberOfSeats) {
}

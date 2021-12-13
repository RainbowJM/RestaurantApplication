package com.restaurant.UserService.core.application.command;

public record RegisterUserCommand(String username, String password, String firstName, String lastName) {
}

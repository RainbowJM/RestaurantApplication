package com.restaurant.UserService.core.application.command;

import com.restaurant.UserService.core.domain.UserRole;

public record ChangeUserCommand(String username, String password, UserRole role, String firstName, String lastName) {
}

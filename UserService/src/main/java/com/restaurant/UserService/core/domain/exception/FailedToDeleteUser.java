package com.restaurant.UserService.core.domain.exception;

public class FailedToDeleteUser extends RuntimeException {
    public FailedToDeleteUser(String username) {
        super(String.format("Failed to delete user with username %s. The user might not exist or some other error occurred!", username));
    }
}

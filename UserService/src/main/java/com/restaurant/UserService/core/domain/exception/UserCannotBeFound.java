package com.restaurant.UserService.core.domain.exception;

public class UserCannotBeFound extends RuntimeException {
    public UserCannotBeFound(String username) {
        super(String.format("Couldn't find user with username of %s", username));
    }
}

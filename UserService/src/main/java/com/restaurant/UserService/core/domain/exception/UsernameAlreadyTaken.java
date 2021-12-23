package com.restaurant.UserService.core.domain.exception;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String username) {
        super(String.format("The username \"%s\" is already taken by another user!", username));
    }
}

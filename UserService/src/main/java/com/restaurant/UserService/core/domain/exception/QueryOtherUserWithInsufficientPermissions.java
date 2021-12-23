package com.restaurant.UserService.core.domain.exception;

public class QueryOtherUserWithInsufficientPermissions extends RuntimeException {
    public QueryOtherUserWithInsufficientPermissions() {
        super("You can't query users if you don't have the sufficient permissions.");
    }
}

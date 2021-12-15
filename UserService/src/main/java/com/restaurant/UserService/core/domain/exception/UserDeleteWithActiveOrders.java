package com.restaurant.UserService.core.domain.exception;

public class UserDeleteWithActiveOrders extends RuntimeException {
    public UserDeleteWithActiveOrders(String username) {
        super(String.format("Can't delete user with username %s if it still has active orders."));
    }
}

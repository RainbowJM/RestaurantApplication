package com.restaurant.UserService.core.domain.exception;

public class OtherServiceRoleReserved extends RuntimeException {
    public OtherServiceRoleReserved() {
        super("You can't upgrade your role to OtherService since it's reserved for other microservices.");
    }
}

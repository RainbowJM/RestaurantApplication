package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.external.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
}

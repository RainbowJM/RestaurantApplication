package com.restaurant.UserService.core.port;

import com.restaurant.UserService.core.domain.external.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrdersFromUser(String username);
}

package com.restaurant.UserService.core.port;

import com.restaurant.UserService.adapter.outgoing.rest.OrderResult;

import java.util.List;

public interface OrderRepository {
    List<OrderResult> getAllOrdersFromUser(String username);
}

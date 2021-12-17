package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAll();
    Optional<Order> findById(String id);

    Optional<Order> deleteOrderById(String orderId);
}

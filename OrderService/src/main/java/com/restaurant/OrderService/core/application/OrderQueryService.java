package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.core.application.query.ListAllOrdersQuery;
import com.restaurant.OrderService.core.application.query.ListRestaurantOrdersQuery;
import com.restaurant.OrderService.core.application.query.OrderQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderQueryService {
    private final OrderRepository repository;

    public OrderQueryService(OrderRepository repository) { this.repository = repository; }

    public Order handle(OrderQuery query){
        Optional<Order> optOrder = this.repository.findById(query.orderId());
        return optOrder.isEmpty() ? null : optOrder.get();
    }

    public List<Order> handle(ListAllOrdersQuery listQuery) { return this.repository.findAll(); }

    public List<Order> handle(ListRestaurantOrdersQuery listQuery) {
        List<Order> orders = this.repository.findByRestaurantId(listQuery.restaurantId());
        return orders.isEmpty() ? null : orders;
    }
}
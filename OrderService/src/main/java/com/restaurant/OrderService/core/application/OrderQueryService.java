package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.core.application.query.ListOrdersQuery;
import com.restaurant.OrderService.core.application.query.ListRestaurantOrdersQuery;
import com.restaurant.OrderService.core.application.query.OrderQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<Order> handle(ListOrdersQuery listQuery) {
        List<Order> orders = new ArrayList<>();
        if(listQuery.optionalOrderId() == null)
            return this.repository.findAll();
        Optional<Order> optOrder = this.repository.findById(listQuery.optionalOrderId());
        if(optOrder.isEmpty())
            throw new OrderNotFound(listQuery.optionalOrderId());
        orders.add(optOrder.get());
        return orders;
    }

    public List<Order> handle(ListRestaurantOrdersQuery listQuery) {
        List<Order> orders = this.repository.findByRestaurantId(listQuery.restaurantId());
        return orders.isEmpty() ? null : orders;
    }
}
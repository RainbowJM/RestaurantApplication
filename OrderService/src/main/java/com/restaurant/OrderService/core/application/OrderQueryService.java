package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.core.application.query.ListAllOrdersQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderQueryService {
    private final OrderRepository repository;

    public OrderQueryService(OrderRepository repository) { this.repository = repository; }

    public List<Order> handle(ListAllOrdersQuery listQuery) { return this.repository.findAll(); }
}

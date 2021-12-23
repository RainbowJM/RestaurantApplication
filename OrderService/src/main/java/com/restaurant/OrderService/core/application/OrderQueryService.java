package com.restaurant.OrderService.core.application;

import com.restaurant.OrderService.adapters.incoming.message.RestaurantEventListener;
import com.restaurant.OrderService.core.application.query.ListOrdersQuery;
import com.restaurant.OrderService.core.application.query.ListRestaurantOrdersQuery;
import com.restaurant.OrderService.core.application.query.OrderQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.exception.OrderNotFound;
import com.restaurant.OrderService.core.domain.exception.OrderWithUnknownRestaurantName;
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
        if(optOrder.isEmpty())
            throw new OrderNotFound(query.orderId());
        return optOrder.get();
    }

    public List<Order> handle(ListOrdersQuery listQuery) {
        if(listQuery.optionalUserId() == null && listQuery.optionalRestaurantId() == null)
            return this.repository.findAll();
        else if(listQuery.optionalUserId() != null)
            return this.repository.findByCustomerId(listQuery.optionalUserId());
        if (!RestaurantEventListener.restaurantExists(listQuery.optionalRestaurantId())) {
            throw new OrderWithUnknownRestaurantName();
        }
        return this.repository.findByRestaurantId(listQuery.optionalRestaurantId());
    }
}
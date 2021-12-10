package com.restaurant.OrderService.application;

import com.restaurant.OrderService.data.OrderDto;
import com.restaurant.OrderService.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    public OrderService() {
    }

    public String placeholder() {
        // Try to have as much logic happen in the domain model
        return "This endpoint is working!";
    }

    public List<OrderDto> getAllReservationsByRestaurant(Long restaurantId){
        List<OrderDto> orderDtos = new ArrayList<>();
        // don't have repo yet
        // List<Order> orders = orderRepository.findAllByRestaurant(restaurantId);
        // if order empty => throw error
        // for order : orders => create orderDto

        OrderDto orderDto1 = new OrderDto();
        OrderDto orderDto2 = new OrderDto();
        OrderDto orderDto3 = new OrderDto();
        orderDtos.add(orderDto1);
        orderDtos.add(orderDto2);
        orderDtos.add(orderDto3);

        return orderDtos;
    }

    public OrderDto getOneOrder(Long orderId){
        // Order order = repo.findById(orderId)
        // if order empty => throw error
        OrderDto orderDto = new OrderDto();
        return orderDto;
    }

    public OrderDto createOrder(OrderDto orderDto){
        Order order = new Order();
        // repo.save(order);
        return orderDto;
    }
}

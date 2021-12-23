package com.restaurant.UserService.adapter.ingoing.message;

import com.restaurant.UserService.adapter.ingoing.message.event.*;
import com.restaurant.UserService.core.application.UserCommandService;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.domain.external.Order;
import com.restaurant.UserService.core.port.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

public class OrderEventListener {
    private final UserQueryService queryService;
    private final UserCommandService commandService;
    private final OrderRepository orderRepository;

    private static List<Order> orders = new ArrayList<>();
    private static boolean initialized = false;

    public OrderEventListener(UserQueryService queryService, UserCommandService commandService, OrderRepository orderRepository) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.orderRepository = orderRepository;
    }

    @EventListener
    public void onReadyEvent(ContextRefreshedEvent event) {
        initializeOrders();
    }

    // Initialization is done at service initialization and when send a ready event from UserService, so that order of startup doesn't matter
    private void initializeOrders() {
        if (!initialized) {
            try {
                orders = orderRepository.getAllOrders();
            }
            catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to UserService because it probably hasn't started yet. UserReadyEvent will need to be used to initialize this server.");
                return;
            }
            initialized = true;
        }
    }

    @RabbitListener(queues = "#{'${message.queue.order-event}'}")
    private void listen(OrderEvent event) {
        switch (event.getEventKey()) {
            case OrderReadyEvent.KEY -> this.initializeOrders();
            case OrderCreatedEvent.KEY -> this.handle((OrderCreatedEvent)event);
            case OrderChangedStatusEvent.KEY -> this.handle((OrderChangedStatusEvent)event);
            case OrderRemovedEvent.KEY -> this.handle((OrderRemovedEvent)event);
        }
    }

    private void handle(OrderCreatedEvent event) {
        orders.add(new Order(event.getOrderId(), event.getCustomerId(), event.getRestaurantId(), event.getStatus(), event.getDeliveryAddress()));
    }

    private void handle(OrderChangedStatusEvent event) {
        for (Order order : orders) {
            if (order.id().equals(event.getOrderId())) {
            }
        }
    }

    private void handle(OrderRemovedEvent event) {
        orders.removeIf(order -> order.id().equals(event.getOrderId()));
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static boolean orderExists(String id) {
        return orders.stream().anyMatch(order -> order.id().equals(id));
    }
}

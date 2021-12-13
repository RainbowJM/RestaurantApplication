package com.restaurant.OrderService.adapters.message;

import com.restaurant.OrderService.core.application.OrderCommandService;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    private final OrderCommandService commandService;

    public OrderEventListener(OrderCommandService commandService) { this.commandService = commandService; }
}

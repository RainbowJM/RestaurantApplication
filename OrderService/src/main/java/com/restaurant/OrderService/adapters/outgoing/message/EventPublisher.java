package com.restaurant.OrderService.adapters.outgoing.message;

import com.restaurant.OrderService.core.domain.event.OrderEvent;
import com.restaurant.OrderService.core.port.OrderEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements OrderEventPublisher {
    private final String orderEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(orderEventExchange, event.getEventKey(), event);
    }
}

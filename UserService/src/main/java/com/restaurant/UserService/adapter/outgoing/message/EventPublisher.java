package com.restaurant.UserService.adapter.outgoing.message;

import com.restaurant.UserService.core.domain.event.UserEvent;
import com.restaurant.UserService.core.port.UserEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements UserEventPublisher {
    private final String userEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(UserEvent event) {
        this.rabbitTemplate.convertAndSend(userEventExchange, event.getEventKey(), event);
    }
}

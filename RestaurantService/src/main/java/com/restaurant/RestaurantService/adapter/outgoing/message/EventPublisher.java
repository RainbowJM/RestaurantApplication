package com.restaurant.RestaurantService.adapter.outgoing.message;

import com.restaurant.RestaurantService.core.domain.event.RestaurantEvent;
import com.restaurant.RestaurantService.core.port.RestaurantEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements RestaurantEventPublisher {
    private final String userEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(RestaurantEvent event) {
        this.rabbitTemplate.convertAndSend(userEventExchange, event.getEventKey(), event);
    }
}

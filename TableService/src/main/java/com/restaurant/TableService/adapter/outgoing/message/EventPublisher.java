package com.restaurant.TableService.adapter.outgoing.message;

import com.restaurant.TableService.core.domain.event.TableEvent;
import com.restaurant.TableService.core.port.TableEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements TableEventPublisher {
    private final String tableEventExchange;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(TableEvent event) {
        this.rabbitTemplate.convertAndSend(tableEventExchange, event.getEventKey(), event);
    }
}

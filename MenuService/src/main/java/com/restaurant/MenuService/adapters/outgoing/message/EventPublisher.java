package com.restaurant.MenuService.adapters.outgoing.message;


import com.restaurant.MenuService.core.domain.event.MenuEvent;
import com.restaurant.MenuService.core.port.MenuEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public class EventPublisher implements MenuEventPublisher {
	private final String menuEventExchange;
	private final RabbitTemplate rabbitTemplate;

	@Override
	public void publish(MenuEvent event) {
		this.rabbitTemplate.convertAndSend(menuEventExchange, event.getEventKey(), event);
	}
}

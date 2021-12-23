package com.restaurant.MenuService.core.port;

import com.restaurant.MenuService.core.domain.event.MenuEvent;

public interface MenuEventPublisher {
	void publish(MenuEvent event);
}

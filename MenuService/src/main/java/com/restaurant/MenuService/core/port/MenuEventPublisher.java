package com.restaurant.MenuService.core.port;

import javax.swing.event.MenuEvent;

public interface MenuEventPublisher {
	void publish(MenuEvent event);
}

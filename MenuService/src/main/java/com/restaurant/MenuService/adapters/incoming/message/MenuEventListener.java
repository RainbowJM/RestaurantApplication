package com.restaurant.MenuService.adapters.incoming.message;

import com.restaurant.MenuService.core.application.MenuCommandService;
import org.springframework.stereotype.Component;

@Component
public class MenuEventListener {
	private final MenuCommandService  menuCommandService;

	public MenuEventListener(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;
	}
}
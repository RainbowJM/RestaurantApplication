package com.restaurant.MenuService.adapters.message;

import com.restaurant.MenuService.core.application.MenuCommandService;
import org.springframework.stereotype.Component;

@Component
public class MenuEventListener {
	private MenuCommandService  menuCommandService;

	public MenuEventListener(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;
	}
}
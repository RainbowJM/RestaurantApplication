package com.restaurant.MenuService.adapters.message;

import com.restaurant.MenuService.core.application.MenuCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuEventListener {
	private final MenuCommandService  menuCommandService;

	public MenuEventListener(MenuCommandService menuCommandService) {
		this.menuCommandService = menuCommandService;
	}
}
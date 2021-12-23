package com.restaurant.MenuService.core.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MenuReadyEvent extends MenuEvent {
	public static final String KEY = "menu.event.ready";

	@Override
	public String getEventKey() {
		return KEY;
	}
}

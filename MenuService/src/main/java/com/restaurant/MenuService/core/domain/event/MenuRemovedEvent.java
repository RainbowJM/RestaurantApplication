package com.restaurant.MenuService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MenuRemovedEvent extends MenuEvent {
	public static final String KEY = "menu.event.removed";

	@Override
	public String getEventKey() {
		return KEY;
	}

	@Getter
	private final String id;
}

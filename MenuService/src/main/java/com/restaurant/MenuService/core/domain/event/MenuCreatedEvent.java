package com.restaurant.MenuService.core.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class MenuCreatedEvent extends MenuEvent{
	public static final String KEY = "menu.event.created";

	@Override
	public String getEventKey() { return KEY; }

	@Getter
	private final String menuId;

	@Getter
	private final List<String> dishIds;

	@Getter
	private final String restaurantId;
}
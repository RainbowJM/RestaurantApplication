package com.restaurant.MenuService.core.domain.event;

import com.restaurant.MenuService.core.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class MenuChangedEvent extends MenuEvent{
	public static final String KEY = "menu.event.changed";

	@Override
	public String getEventKey() { return KEY; }

	@Getter
	private final String id;

	@Getter
	private final List<String> dishIds;

	@Getter
	private final String restaurantId;
}

package com.restaurant.MenuService.core.application.command;

import com.restaurant.MenuService.core.domain.Dish;

import java.util.List;

public record AddMenuCommand(String menuId, List<Dish> dishes, String restaurantId) {
}

package com.restaurant.MenuService.core.application.command;

public record DeleteDishFromMenuCommand(String dishId, String menuId) {
}

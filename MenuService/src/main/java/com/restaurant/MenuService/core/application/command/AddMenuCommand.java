package com.restaurant.MenuService.core.application.command;

import java.util.List;

public record AddMenuCommand(long menuId, List dishes, long restaurantId) {
}

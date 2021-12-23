package com.restaurant.OrderService.core.port;

import com.restaurant.OrderService.core.domain.external.Menu;

import java.util.List;

public interface MenuRepository {
    List<Menu> getAllMenus();
}

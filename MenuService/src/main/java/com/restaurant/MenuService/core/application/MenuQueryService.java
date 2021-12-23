package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MenuQueryService {

    private final MenuRepository menuRepository;

    public MenuQueryService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Optional<Menu> getMenuById(String menuId){
        return this.menuRepository.findMenuById(menuId);
    }

    public Optional<Menu> getMenuByRestaurantId(String restaurantId){
        return this.menuRepository.findMenuByRestaurantId(restaurantId);
    }

    public Dish getDishByMenu(String menuId, String dishId){
        return this.menuRepository.findById(menuId).get().getDishById(dishId);
    }

}

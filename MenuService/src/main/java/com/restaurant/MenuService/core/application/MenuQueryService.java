package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.core.application.query.GetDishByMenuQuery;
import com.restaurant.MenuService.core.application.query.GetMenuQuery;
import com.restaurant.MenuService.core.application.query.GetAllMenus;
import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuQueryService {

    private final MenuRepository menuRepository;

    public MenuQueryService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Optional<Menu> handle(GetMenuQuery getMenuQuery){
        return this.menuRepository.findMenuById(getMenuQuery.menuId());
    }

    public List<Menu> handle(GetAllMenus getAllMenus){
        if (getAllMenus.restaurantId() != null){
            return this.menuRepository.findAllByRestaurantId(getAllMenus.restaurantId());
        }
        else {
            return this.menuRepository.getAll();
        }
    }

    public Dish handle(GetDishByMenuQuery getDishByMenuQuery){
        return this.menuRepository.findById(getDishByMenuQuery.menuId()).get().getDishById(getDishByMenuQuery.dishId());
    }

}

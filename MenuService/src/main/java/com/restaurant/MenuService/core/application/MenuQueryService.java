package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuQueryService {

    private final MenuRepository menuRepository;

    public MenuQueryService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getAllMenusByRestaurantId(long restaurantId){
        return this.menuRepository.findAllByRestaurantId(restaurantId);
    }

}

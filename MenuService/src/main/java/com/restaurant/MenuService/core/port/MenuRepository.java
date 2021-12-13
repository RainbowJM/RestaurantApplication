package com.restaurant.MenuService.core.port;

import com.restaurant.MenuService.core.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuRepository extends MongoRepository<Menu, Long> {
	List<Menu> findAllByRestaurantId(long restaurantId);
	Menu findById(long menuId);
	Boolean deleteMenuById(long menuId);
}

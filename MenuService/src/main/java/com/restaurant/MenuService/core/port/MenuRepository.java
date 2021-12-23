package com.restaurant.MenuService.core.port;

import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends MongoRepository<Menu, String> {
	List<Menu> findAllByRestaurantId(String restaurantId);
	List<Menu> getAll();
	Optional<Menu> findMenuById(String menuId);
	boolean existsById(String menuId);
	void deleteMenuById(String menuId);
}

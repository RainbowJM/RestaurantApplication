package com.restaurant.MenuService.core.port;

import com.restaurant.MenuService.core.domain.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends MongoRepository<Menu, String> {
	List<Menu> findAllByRestaurantId(long restaurantId);
	Optional<Menu> findById(long menuId);
	boolean existsById(long menuId);
	Optional<Menu> deleteMenuById(long menuId);
}

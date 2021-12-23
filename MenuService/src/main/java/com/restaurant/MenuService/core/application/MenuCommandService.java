package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.adapters.incoming.message.RestaurantEventListener;
import com.restaurant.MenuService.core.application.command.AddDishToMenuCommand;
import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteDishFromMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.domain.exceptions.InvalidRestaurantIdException;
import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Optional;

@Service
@Transactional
public class MenuCommandService {
	private final MenuRepository menuRepository;

	public MenuCommandService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public Menu handle(AddMenuCommand addMenuCommand) throws InvalidRestaurantIdException {
		if (RestaurantEventListener.restaurantExists(addMenuCommand.restaurantId())) {
			Menu newMenu = new Menu(addMenuCommand.restaurantId());
			return this.menuRepository.save(newMenu);
		}
		else throw new InvalidRestaurantIdException(addMenuCommand.restaurantId());
	}

	public void handle(DeleteMenuCommand deleteMenuCommand) throws InstanceNotFoundException {
		if (menuRepository.existsById(deleteMenuCommand.menuId())){
			menuRepository.deleteMenuById(deleteMenuCommand.menuId());
		}
		else throw new InstanceNotFoundException(deleteMenuCommand.menuId());
	}

	public void handle(AddDishToMenuCommand addDishToMenuCommand) throws InstanceNotFoundException, InstanceAlreadyExistsException {
		if(menuRepository.existsById(addDishToMenuCommand.menuId())){
			Dish dish = new Dish(addDishToMenuCommand.dishId(), addDishToMenuCommand.name(), addDishToMenuCommand.price(), addDishToMenuCommand.ingredients(), addDishToMenuCommand.calories());
			Optional<Menu> optional = menuRepository.findMenuById(addDishToMenuCommand.menuId());
			Menu menu = optional.get();
			this.menuRepository.deleteMenuById(addDishToMenuCommand.menuId());
			menu.AddDishToDishes(dish);
			this.menuRepository.save(menu);
		}
		else throw new InstanceNotFoundException(addDishToMenuCommand.menuId());
	}

	public void handle(DeleteDishFromMenuCommand deleteDishFromMenu) throws InstanceNotFoundException{
		if (menuRepository.existsById(deleteDishFromMenu.menuId())){
			Optional<Menu> optional = menuRepository.findMenuById(deleteDishFromMenu.menuId());
			Menu menu = optional.get();
			this.menuRepository.deleteMenuById(deleteDishFromMenu.menuId());
			menu.deleteDishById(deleteDishFromMenu.dishId());
			this.menuRepository.save(menu);
		}
		else throw new InstanceNotFoundException();
	}

}
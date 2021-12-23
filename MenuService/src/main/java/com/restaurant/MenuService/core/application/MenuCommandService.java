package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.adapters.incoming.message.RestaurantEventListener;
import com.restaurant.MenuService.adapters.outgoing.message.EventPublisher;
import com.restaurant.MenuService.core.application.command.AddDishToMenuCommand;
import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteDishFromMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.domain.event.MenuChangedEvent;
import com.restaurant.MenuService.core.domain.event.MenuCreatedEvent;
import com.restaurant.MenuService.core.domain.event.MenuRemovedEvent;
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
	private final EventPublisher eventPublisher;

	public MenuCommandService(MenuRepository menuRepository, EventPublisher eventPublisher) {
		this.menuRepository = menuRepository;
		this.eventPublisher = eventPublisher;
	}

	public Menu handle(AddMenuCommand addMenuCommand) throws InvalidRestaurantIdException {
		if (RestaurantEventListener.restaurantExists(addMenuCommand.restaurantId())) {
			Menu newMenu = new Menu(addMenuCommand.restaurantId());
			this.eventPublisher.publish(new MenuCreatedEvent(newMenu.getId(), newMenu.getAllDishIds(), newMenu.getRestaurantId()));
			return this.menuRepository.save(newMenu);
		}
		else throw new InvalidRestaurantIdException(addMenuCommand.restaurantId());
	}

	public void handle(DeleteMenuCommand deleteMenuCommand) throws InstanceNotFoundException {
		if (menuRepository.existsById(deleteMenuCommand.menuId())){
			Menu menu = menuRepository.findMenuById(deleteMenuCommand.menuId()).get();
			menuRepository.deleteMenuById(deleteMenuCommand.menuId());
			this.eventPublisher.publish(new MenuRemovedEvent(menu.getId()));
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
			this.eventPublisher.publish(new MenuChangedEvent(menu.getId(), menu.getAllDishIds(), menu.getRestaurantId()));
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
			this.eventPublisher.publish(new MenuChangedEvent(menu.getId(), menu.getAllDishIds(), menu.getRestaurantId()));
		}
		else throw new InstanceNotFoundException();
	}

}
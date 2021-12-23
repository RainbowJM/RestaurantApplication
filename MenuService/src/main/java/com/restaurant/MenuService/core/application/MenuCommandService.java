package com.restaurant.MenuService.core.application;

import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.port.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

@Service
@Transactional
public class MenuCommandService {
	private final MenuRepository menuRepository;

	public MenuCommandService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public Menu handle(AddMenuCommand addMenuCommand) throws InstanceAlreadyExistsException {
		if (menuRepository.existsById(addMenuCommand.menuId())){
			throw new InstanceAlreadyExistsException(addMenuCommand.menuId());
		}

		Menu newMenu = new Menu(addMenuCommand.restaurantId());
		return this.menuRepository.save(newMenu);
	}

	public void handle(DeleteMenuCommand deleteMenuCommand) throws InstanceNotFoundException {
		if (menuRepository.existsById(deleteMenuCommand.menuId())){
			menuRepository.deleteMenuById(deleteMenuCommand.menuId());
		}
		else throw new InstanceNotFoundException(deleteMenuCommand.menuId());
	}

}
package com.restaurant.MenuService.adapters.rest;

import com.restaurant.MenuService.adapters.rest.requestDTO.DishRequest;
import com.restaurant.MenuService.adapters.rest.requestDTO.MenuRequest;
import com.restaurant.MenuService.core.application.MenuCommandService;
import com.restaurant.MenuService.core.application.MenuQueryService;
import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path="/menu")
public class MenuRestController {
	public final MenuQueryService menuQueryService;
	public final MenuCommandService menuCommandService;

	public MenuRestController(MenuQueryService menuQueryService, MenuCommandService menuCommandService) {
		this.menuQueryService = menuQueryService;
		this.menuCommandService = menuCommandService;
	}

	@GetMapping(path="/{id}/")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Menu> getMenuById(@PathVariable String id){return this.menuQueryService.getMenuById(id);
	}

	@GetMapping(path="/restaurant/{id}/")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Menu> getMenuByRestaurantId(@PathVariable String id){
		return this.menuQueryService.getMenuByRestaurantId(id);
	}

	@PostMapping(path="/")
	@ResponseStatus(HttpStatus.CREATED)
	public Menu createMenu(@Valid @RequestBody MenuRequest menuRequest) throws InstanceAlreadyExistsException {
		return this.menuCommandService.createMenu(new AddMenuCommand(menuRequest.id, menuRequest.dishes, menuRequest.restaurantId));
	}

	@DeleteMapping(path = "/{id}/")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMenu(@PathVariable String id) throws InstanceNotFoundException {this.menuCommandService.DeleteMenu(new DeleteMenuCommand(id));}

	@PatchMapping(path = "/{id}/")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateMenu(@RequestBody MenuRequest menuRequest, @PathVariable String id) throws InstanceNotFoundException {
		this.menuCommandService.EditMenu(new AddMenuCommand(menuRequest.id, menuRequest.dishes, menuRequest.restaurantId));
	}

	@GetMapping(path = "/{menuId}/{dishId}")
	@ResponseStatus(HttpStatus.OK)
	public Dish getDishfromMenu(@PathVariable String menuId, @PathVariable String dishId){
		return this.menuQueryService.getMenuById(menuId).get().getDishById(dishId);
	}

	@PostMapping("/{id}/")
	@ResponseStatus(HttpStatus.CREATED)
	public void addDishToMenu(@RequestBody DishRequest dishRequest, @PathVariable String id){
		this.menuQueryService.getMenuById(id).get().getDish().add(new Dish(dishRequest.id, dishRequest.naam, dishRequest.prijs, dishRequest.ingredienten, dishRequest.calorien));
	}

	@DeleteMapping("/{id}/{dishId}/")
	@ResponseStatus(HttpStatus.OK)
	public void deleteDishFromMenu(@PathVariable String id, @PathVariable String dishId){
		this.menuQueryService.getMenuById(id).get().deleteDishById(dishId);
	}
}

package com.restaurant.MenuService.adapters.incoming.rest;

import com.restaurant.MenuService.adapters.incoming.rest.requestDTO.DishRequest;
import com.restaurant.MenuService.adapters.incoming.rest.requestDTO.MenuRequest;
import com.restaurant.MenuService.core.application.MenuCommandService;
import com.restaurant.MenuService.core.application.MenuQueryService;
import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.application.query.GetDishByMenuQuery;
import com.restaurant.MenuService.core.application.query.GetMenuQuery;
import com.restaurant.MenuService.core.application.query.GetAllMenus;
import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

	@GetMapping(path="/")
	@RolesAllowed({"User", "Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public List<Menu> getAllMenus(@RequestParam(required = false) String restaurant){
		return this.menuQueryService.handle(new GetAllMenus(restaurant));
	}

	@GetMapping(path="/{menuId}/")
	@RolesAllowed({"User", "Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public Optional<Menu> getMenuById(@PathVariable String menuId){return this.menuQueryService.handle(new GetMenuQuery(menuId));
	}

	@GetMapping(path = "/{menuId}/{dishId}")
	@RolesAllowed({"User", "Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public Dish getDishfromMenu(@PathVariable String menuId, @PathVariable String dishId){
		return this.menuQueryService.handle(new GetDishByMenuQuery(menuId, dishId));
	}

	@PostMapping(path="/")
	@RolesAllowed({"Staff", "Management"})
	@ResponseStatus(HttpStatus.CREATED)
	public Menu createMenu(@Valid @RequestBody MenuRequest menuRequest) throws InstanceAlreadyExistsException {
		return this.menuCommandService.handle(new AddMenuCommand(menuRequest.id, menuRequest.restaurantId));
	}

	@PostMapping("/{menuId}/")
	@RolesAllowed({"Staff", "Management"})
	@ResponseStatus(HttpStatus.CREATED)
	public DishRequest addDishToMenu(@RequestBody DishRequest dishRequest, @PathVariable String menuId){
		this.menuQueryService.handle(new GetMenuQuery(menuId)).get().getDishes().add(new Dish(dishRequest.id, dishRequest.naam, dishRequest.prijs, dishRequest.ingredienten, dishRequest.calories));
		return dishRequest;
	}

	@DeleteMapping(path = "/{menuId}/")
	@RolesAllowed({"Staff", "Management"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteMenu(@PathVariable String menuId) throws InstanceNotFoundException {this.menuCommandService.handle(new DeleteMenuCommand(menuId));}


	@DeleteMapping("/{menuId}/{dishId}/")
	@RolesAllowed({"Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteDishFromMenu(@PathVariable String menuId, @PathVariable String dishId){
		this.menuQueryService.handle(new GetMenuQuery(menuId)).get().deleteDishById(dishId);
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint or your login token has expired."));
	}
}
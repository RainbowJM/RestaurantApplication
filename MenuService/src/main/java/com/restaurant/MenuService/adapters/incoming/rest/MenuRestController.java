package com.restaurant.MenuService.adapters.incoming.rest;

import com.restaurant.MenuService.adapters.incoming.rest.requestDTO.DishRequest;
import com.restaurant.MenuService.adapters.incoming.rest.requestDTO.MenuRequest;
import com.restaurant.MenuService.adapters.incoming.rest.responseDTO.DishResponse;
import com.restaurant.MenuService.core.application.MenuCommandService;
import com.restaurant.MenuService.core.application.MenuQueryService;
import com.restaurant.MenuService.core.application.command.AddDishToMenuCommand;
import com.restaurant.MenuService.core.application.command.AddMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteDishFromMenuCommand;
import com.restaurant.MenuService.core.application.command.DeleteMenuCommand;
import com.restaurant.MenuService.core.application.query.GetDishByMenuQuery;
import com.restaurant.MenuService.core.application.query.GetMenuQuery;
import com.restaurant.MenuService.core.application.query.GetAllMenus;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.domain.exceptions.InvalidRestaurantIdException;
import io.jsonwebtoken.ExpiredJwtException;
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
	public DishResponse getDishfromMenu(@PathVariable String menuId, @PathVariable String dishId){
		return this.menuQueryService.handle(new GetDishByMenuQuery(menuId, dishId));
	}

	@PostMapping(path="/")
	@RolesAllowed({"Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.CREATED)
	public Menu createMenu(@Valid @RequestBody MenuRequest menuRequest) throws InvalidRestaurantIdException {
		return this.menuCommandService.handle(new AddMenuCommand(menuRequest.restaurantId));
	}

	@PostMapping("/{menuId}/")
	@RolesAllowed({"Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.CREATED)
	public DishRequest addDishToMenu(@RequestBody DishRequest dishRequest, @PathVariable String menuId) throws InstanceNotFoundException, InstanceAlreadyExistsException {
		this.menuCommandService.handle(new AddDishToMenuCommand(menuId, dishRequest.id, dishRequest.name, dishRequest.price, dishRequest.ingredients, dishRequest.calories));
		return dishRequest;
	}

	@DeleteMapping(path = "/{menuId}/")
	@RolesAllowed({"Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteMenu(@PathVariable String menuId) throws InstanceNotFoundException {this.menuCommandService.handle(new DeleteMenuCommand(menuId));}

	@DeleteMapping("/{menuId}/{dishId}/")
	@RolesAllowed({"Staff", "Management", "OtherService"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteDishFromMenu(@PathVariable String menuId, @PathVariable String dishId) throws InstanceNotFoundException {
		this.menuCommandService.handle(new DeleteDishFromMenuCommand(dishId, menuId));
	}

	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint or your login token has expired."));
	}

	@ExceptionHandler({ExpiredJwtException.class})
	public ResponseEntity<Map<String, String>> handleOldTokenException(ExpiredJwtException exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied because your login has expired."));
	}

	@ExceptionHandler({org.springframework.security.access.AccessDeniedException.class})
	public ResponseEntity<Map<String, String>> handleBadTokenException(Exception exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint."));
	}

	@ExceptionHandler({InvalidRestaurantIdException.class})
	public ResponseEntity<Map<String, String>> handleOldTokenException(InvalidRestaurantIdException exception) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", exception.getMessage()));
	}
}

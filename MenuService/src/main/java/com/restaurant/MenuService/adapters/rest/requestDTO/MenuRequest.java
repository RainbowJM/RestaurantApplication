package com.restaurant.MenuService.adapters.rest.requestDTO;

import com.restaurant.MenuService.core.domain.Dish;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class MenuRequest {
	@NotBlank(message = "restaurant id cannot be empty")
	@Size(min=1, max=1, message = "the restaurant id can only be on number")
	public String restaurantId;
	@NotBlank(message = "the menu id cannot be empty")
	@Size(min=1, max = 1, message = "the menu id can only be one number")
	public String id;
	@NotBlank(message = "the menu cannot be empty")
	public List<Dish> dishes;
}

package com.restaurant.MenuService.adapters.rest.requestDTO;

import com.restaurant.MenuService.core.domain.Dish;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DishRequest {
	@NotBlank(message = "There must be a dish")
	public Dish dish;
}
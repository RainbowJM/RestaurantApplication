package com.restaurant.MenuService.adapters.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class DishRequest {
	@NotBlank(message = "dish should get an id")
	public String id;
	@NotBlank(message = "dish needs to have a name")
	public String naam;
	@NotBlank(message = "dish must have a price")
	public Double prijs;
	@NotBlank(message = "a dish must have ingredients")
	public String ingredienten;
	@NotBlank(message = "There must be a calorie amount submitted")
	public int calories;

}
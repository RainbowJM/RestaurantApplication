package com.restaurant.MenuService.adapters.incoming.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DishRequest {
	@NotBlank(message = "dish should get an id")
	public String id;
	@NotBlank(message = "dish needs to have a name")
	public String name;
	@NotBlank(message = "dish must have a price")
	public Double price;
	@NotBlank(message = "a dish must have ingredients")
	public String ingredients;
	@NotBlank(message = "There must be a calorie amount submitted")
	public int calories;

}
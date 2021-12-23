package com.restaurant.MenuService.adapters.incoming.rest.responseDTO;

import javax.validation.constraints.NotBlank;

public class DishResponse {
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

	public DishResponse(String id, String name, Double price, String ingredients, int calories) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
		this.calories = calories;
	}
}

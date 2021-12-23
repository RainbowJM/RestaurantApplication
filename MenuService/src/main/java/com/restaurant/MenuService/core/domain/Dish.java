package com.restaurant.MenuService.core.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

public class Dish {
	@Getter
	@Setter
	private String id;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private Double price;
	@Getter
	@Setter
	private String ingredients;
	@Getter
	@Setter
	private int calories;

	public Dish(String id, String name, Double price, String ingredients, int calories){
		this.id = id;
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
		this.calories = calories;
	}

}

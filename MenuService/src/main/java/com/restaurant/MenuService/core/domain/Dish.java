package com.restaurant.MenuService.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Dish {
	@Getter
	@Setter
	private String id;
	@Getter
	@Setter
	private String naam;
	@Getter
	@Setter
	private Double price;
	@Getter
	@Setter
	private String ingredients;
	@Getter
	@Setter
	private int calories;

	public Dish(String id, String naam, Double prijs, String ingredienten, int calorie){
		this.id = id;
		this.naam = naam;
		this.price = prijs;
		this.ingredients = ingredienten;
		this.calories = calorie;
	}

	public String getId() {
		return id;
	}
}

package com.restaurant.MenuService.core.domain;

import java.util.List;

public class Dish {
	private String id;
	private String naam;
	private Double price;
	private List<String> ingredients;
	private int calories;

	public Dish(String id, String naam, Double prijs, List<String> ingredienten, int calorie){
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

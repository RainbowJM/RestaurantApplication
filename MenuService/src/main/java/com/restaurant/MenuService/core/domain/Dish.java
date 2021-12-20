package com.restaurant.MenuService.core.domain;

import java.util.List;

public class Dish {
	private String id;
	private String naam;
	private Double prijs;
	private List<String> ingredienten;
	private int calorie;

	public Dish(String id, String naam, Double prijs, List<String> ingredienten, int calorie){
		this.id = id;
		this.naam = naam;
		this.prijs = prijs;
		this.ingredienten = ingredienten;
		this.calorie = calorie;
	}

	public String getId() {
		return id;
	}
}

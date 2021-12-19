package com.restaurant.MenuService.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Dish {

	@Id
	@Getter
	private String id;

	@Getter
	@Setter
	private String naam;

	@Getter
	@Setter
	private Double prijs;

	@Getter
	@Setter
	private List<String> ingredienten;

	@Getter
	@Setter
	private int calorie;

	public Dish(String id, String naam, Double prijs, List<String> ingredienten, int calorie){
		this.id = id;
		this.naam = naam;
		this.prijs = prijs;
		this.ingredienten = ingredienten;
		this.calorie = calorie;
	}
}

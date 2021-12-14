package com.restaurant.MenuService.domain;

import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.domain.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuTest {
	private String id;
	private List<String> dishes;
	private String restaurantId;

	@BeforeEach
	public void initialize(){
		this.id = "1";
		this.dishes.add("pancakes");
		this.dishes.add("wafels");
		this.dishes.add("eggs and bacon");
		this.restaurantId = "1";
	}

	@Test
	@DisplayName("Should create a menu")
	void shouldCreateAmenu(){
		assertDoesNotThrow(() -> new Menu(id, dishes, restaurantId));
	}

	@Test
	@DisplayName("should throw restaurant Id exception")
	void shouldThrowRestaurantIdException() {
		assertThrows(InvalidRestaurantIdException.class , () -> new Menu(id, dishes, "-1"));
	}
}

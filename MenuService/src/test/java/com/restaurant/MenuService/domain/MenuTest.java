package com.restaurant.MenuService.domain;

import com.restaurant.MenuService.core.domain.Dish;
import com.restaurant.MenuService.core.domain.Menu;
import com.restaurant.MenuService.core.domain.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuTest {
	private String id;
	private List<Dish> dishes;
	private String restaurantId;

	@BeforeEach
	public void initialize(){
		this.dishes.add(new Dish("1","kip",10.00,"kip, boter, kruiden",1000));
		this.dishes.add(new Dish("2","vis",11.99,"kip, boter, kruiden", 1200));
		this.dishes.add(new Dish("3","spaghetti",12.50,"kip, boter, kruiden", 1400));
		this.restaurantId = "1";
	}

	@Test
	@DisplayName("Should create a menu")
	void shouldCreateAmenu(){
		assertDoesNotThrow(() -> new Menu(restaurantId));
	}

	@Test
	@DisplayName("should throw restaurant Id exception")
	void shouldThrowRestaurantIdException() {
		assertThrows(InvalidRestaurantIdException.class , () -> new Menu("-1"));
	}
}

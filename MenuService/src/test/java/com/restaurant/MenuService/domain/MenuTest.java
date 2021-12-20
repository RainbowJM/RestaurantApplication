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
		this.id = "1";
		this.dishes.add(new Dish("1","kip",10.00,Arrays.asList("kip","kruiden", "boter"),1000));
		this.dishes.add(new Dish("2","vis",11.99,Arrays.asList("vis","kruiden", "olijf olie"), 1200));
		this.dishes.add(new Dish("3","spaghetti",12.50,Arrays.asList("pasta","kruiden", "olijf olie", "boter", "tomatensaus", "gehakt"), 1400));
		this.restaurantId = "1";
	}

	@Test
	@DisplayName("Should create a menu")
	void shouldCreateAmenu(){
		assertDoesNotThrow(() -> new Menu(id, restaurantId));
	}

	@Test
	@DisplayName("should throw restaurant Id exception")
	void shouldThrowRestaurantIdException() {
		assertThrows(InvalidRestaurantIdException.class , () -> new Menu(id, "-1"));
	}
}

package com.restaurant.MenuService.core.domain.exceptions;

public class RestaurantNotFoundException extends RuntimeException{
	public RestaurantNotFoundException(String id){
		super(String.format("Restaurant not found %s", id));
	}
}

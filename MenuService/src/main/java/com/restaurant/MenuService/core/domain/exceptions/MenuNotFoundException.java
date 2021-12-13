package com.restaurant.MenuService.core.domain.exceptions;

public class MenuNotFoundException extends RuntimeException{
	public MenuNotFoundException(long id){
		super(String.format("Restaurant not found %s", id));
	}
}

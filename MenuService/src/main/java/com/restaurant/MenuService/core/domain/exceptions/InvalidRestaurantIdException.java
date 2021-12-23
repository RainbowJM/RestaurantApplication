package com.restaurant.MenuService.core.domain.exceptions;

public class InvalidRestaurantIdException extends RuntimeException{
	public InvalidRestaurantIdException(String id){
		super(String.format("Restaurant id %s is invalid", id));
	}
}

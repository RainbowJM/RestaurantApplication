package com.restaurant.MenuService.adapters.incoming.message.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MenuRequest {
	@NotBlank(message = "restaurant id cannot be empty")
	public String restaurantId;
	@NotBlank(message = "the menu id cannot be empty")
	public String id;
}

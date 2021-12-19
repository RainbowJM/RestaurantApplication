package com.restaurant.RestaurantService.adapter.ingoing.rest.requestDTO;

import javax.validation.constraints.NotBlank;

public class CreateRestaurantRequest {
    @NotBlank(message="You need to provide a restaurant name")
    public String name;

    @NotBlank(message="You need to provide an address for the restaurant")
    public String address;
}

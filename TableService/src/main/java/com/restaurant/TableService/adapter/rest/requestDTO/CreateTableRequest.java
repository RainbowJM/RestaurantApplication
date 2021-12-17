package com.restaurant.TableService.adapter.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateTableRequest {
    @NotBlank(message="You need to provide a restaurant id to create the table at")
    public String restaurantName;

    @NotBlank(message="You need to provide a location for this table in the restaurant")
    public String location;

    @Min(value=1, message="Table needs to have at least 1 seat")
    public Long numberOfSeats;
}

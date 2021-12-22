package com.restaurant.TableService.adapter.ingoing.rest.requestDTO;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ModifyTableRequest {
    @NotBlank
    public String tableId;

    @NotBlank
    public String restaurantId;

    @NotBlank
    public String location;

    @Min(value = 1)
    public Long numberOfSeats;

}

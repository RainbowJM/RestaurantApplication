package com.restaurant.TableService.adapter.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
public class ModifyTableRequest {
    String tableId;
    String restaurantId;
    String location;
    @Min(value = 1)
    Long numberOfSeats;

}

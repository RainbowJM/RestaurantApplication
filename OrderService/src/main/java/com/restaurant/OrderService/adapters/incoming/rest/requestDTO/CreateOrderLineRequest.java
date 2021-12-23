package com.restaurant.OrderService.adapters.incoming.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
public class CreateOrderLineRequest {
    @NotBlank(message = "productId can't be empty")
    String productId;
    @NotBlank(message = "You need to provide an amount")
    @Size(min = 1, message = "Amount can't be 0")
    int amount;
    @Null
    float price;
}
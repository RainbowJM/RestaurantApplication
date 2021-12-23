package com.restaurant.OrderService.core.domain.external;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Menu {
    private String id;
    private List<String> dishIds;
    private String restaurantId;
}

package com.restaurant.OrderService.core.domain.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuResult {
    private String id;
    private List<String> dishIds;
    private String restaurantId;
}

package com.restaurant.OrderService.core.domain.external;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class Restaurant {
    private String name;
    private String address;
}

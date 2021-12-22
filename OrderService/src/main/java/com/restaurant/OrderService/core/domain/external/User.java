package com.restaurant.OrderService.core.domain.external;

import lombok.*;

@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class User {
    private String username;
    private String role;

    private String firstName;
    private String lastName;
}

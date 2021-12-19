package com.restaurant.RestaurantService.core.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Restaurant")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Restaurant {
    @Id
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String address;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

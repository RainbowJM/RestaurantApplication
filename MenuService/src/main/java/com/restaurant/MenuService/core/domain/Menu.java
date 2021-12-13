package com.restaurant.MenuService.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collation = "Menu")
public class Menu {

    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private List<Long> dishes;

    @Id
    @Getter
    private Long restaurantId;

    public Menu() {}

    public Menu(Long id, List<Long> dishes, Long restaurantId) {
        this.id = id;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}

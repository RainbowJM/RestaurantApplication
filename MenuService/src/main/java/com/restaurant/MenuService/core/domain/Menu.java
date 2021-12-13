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
    private String id;

    @Getter
    @Setter
    private List<String> dishes;

    @Setter
    @Getter
    private String restaurantId;

    public Menu() {}

    public Menu(String id, List<String> dishes, String restaurantId) {
        this.id = id;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}

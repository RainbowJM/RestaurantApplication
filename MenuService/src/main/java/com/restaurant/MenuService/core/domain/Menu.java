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
    private long id;

    @Getter
    @Setter
    private List dishes;

    @Id
    @Getter
    private long restaurantId;

    public Menu() {}

    public Menu(long id, List dishes, long restaurantId) {
        this.id = id;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}

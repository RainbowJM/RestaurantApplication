package com.restaurant.MenuService.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.lang.reflect.Array;
import java.util.List;

@Document(collation = "Menu")
public class Menu {

    @Id
    private long id;
    private List dishes;
    private long restaurantId;

    public Menu() {}

    public Menu(long id, List dishes, long restaurantId) {
        this.id = id;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}

package com.restaurant.MenuService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.processing.Generated;
import java.lang.reflect.Array;
import java.util.List;

public class Menu {

    private long id;
    private Array dishes;
    private long restaurantId;

    public Menu() {}

    public Menu(long id, Array dishes, long restaurantId) {
        this.id = id;
        this.dishes = dishes;
        this.restaurantId = restaurantId;
    }
}

package com.restaurant.MenuService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Menu")
public class Menu {
    @Id
    public String id;

    @Field("test")
    private String test;

    public Menu() {
    }

    public Menu(String test) {
        this.test = test;
    }
}

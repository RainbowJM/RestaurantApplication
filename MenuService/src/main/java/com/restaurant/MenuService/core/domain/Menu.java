package com.restaurant.MenuService.core.domain;

import com.restaurant.MenuService.core.domain.exceptions.InvalidRestaurantIdException;
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

    @Getter
    private String restaurantId;

    public Menu() {}

    public Menu(String id, List<String> dishes, String restaurantId) {
        this.id = id;
        this.dishes = dishes;
        setRestaurantId(restaurantId);
    }

    public void setRestaurantId(String restaurantId){
        try {
            if (Integer.parseInt(restaurantId) < 1) {
                throw new InvalidRestaurantIdException(restaurantId);
            }
            this.restaurantId= restaurantId;
        } catch (NumberFormatException e){
            System.out.println("input string cannot be parsed");
        }
    }
}

package com.restaurant.MenuService.core.domain;

import com.restaurant.MenuService.core.domain.exceptions.InvalidRestaurantIdException;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Menu")
public class Menu {

    @Id
    @Getter
    @Generated
    private String id;

    @Getter
    @Setter
    private List<Dish> dishes = new ArrayList<>();

    @Getter
    private String restaurantId;

    public Menu(String restaurantId) {
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

    public void AddDishToDishes(Dish dish) throws InstanceAlreadyExistsException {
        if (this.dishes.contains(dish)){
            throw new InstanceAlreadyExistsException();
        }
        else {
            this.dishes.add(dish);
        }
    }

    public Dish getDishById (String id){
        for (Dish dish : this.dishes){
            if (dish.getId().equals(id)){
                return dish;
            }
        }
        return null;
    }

    public void deleteDishById (String id){
        this.dishes.removeIf(dish -> dish.getId().equals(id));
    }
}

package com.restaurant.RestaurantService.core.port;

import com.restaurant.RestaurantService.core.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    List<Restaurant> findAll();
    Restaurant findByName(String name);

    Restaurant deleteByName(String name);
}

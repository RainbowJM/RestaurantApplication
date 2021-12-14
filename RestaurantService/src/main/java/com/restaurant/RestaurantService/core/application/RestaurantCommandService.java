package com.restaurant.RestaurantService.core.application;

import com.restaurant.RestaurantService.core.application.command.CreateRestaurantCommand;
import com.restaurant.RestaurantService.core.application.command.DeleteRestaurantCommand;
import com.restaurant.RestaurantService.core.domain.Restaurant;
import com.restaurant.RestaurantService.core.port.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantCommandService {
    private final RestaurantRepository repository;

    public RestaurantCommandService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant handle(CreateRestaurantCommand createRestaurantCommand) {
        return this.repository.save(new Restaurant(createRestaurantCommand.name(), createRestaurantCommand.location()));
    }

    public void handle(DeleteRestaurantCommand deleteRestaurantCommand) {
        this.repository.deleteByName(deleteRestaurantCommand.name());
    }
}

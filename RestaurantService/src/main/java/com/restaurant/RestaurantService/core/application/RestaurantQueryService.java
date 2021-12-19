package com.restaurant.RestaurantService.core.application;

import com.restaurant.RestaurantService.core.application.query.ListRestaurantsQuery;
import com.restaurant.RestaurantService.core.domain.Restaurant;
import com.restaurant.RestaurantService.core.port.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantQueryService {
    private final RestaurantRepository repository;

    public RestaurantQueryService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> handle(ListRestaurantsQuery listRestaurantsQuery) {
        return this.repository.findAll();
    }
}

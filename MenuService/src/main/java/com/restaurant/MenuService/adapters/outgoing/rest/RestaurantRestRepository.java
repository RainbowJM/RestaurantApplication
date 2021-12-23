package com.restaurant.MenuService.adapters.outgoing.rest;

import com.restaurant.MenuService.core.domain.external.Restaurant;
import com.restaurant.MenuService.core.port.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class RestaurantRestRepository implements RestaurantRepository {
    private final String privateToken;
    private final String restaurantEndpointPath;
    private final RestTemplate client;

    @Override
    public List<Restaurant> getAllRestaurants() {
        URI path = URI.create(this.restaurantEndpointPath + "/restaurant/");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);

        ResponseEntity<Restaurant[]> restaurantsResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), Restaurant[].class);

        if (restaurantsResponse.getStatusCode() == HttpStatus.OK && restaurantsResponse.getBody() != null) {
            return List.of(restaurantsResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

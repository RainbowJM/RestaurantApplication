package com.restaurant.TableService.adapter.outgoing.rest;

import com.restaurant.TableService.core.domain.external.Restaurant;
import com.restaurant.TableService.core.port.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RestaurantRestRepository implements RestaurantRepository {
    private final String privateToken;
    private final String restaurantEndpointPath;
    private final RestTemplate client;

    @Override
    public List<Restaurant> getRestaurantFromTable(String id) {
        URI path = URI.create(this.restaurantEndpointPath + "/restaurant/?user=");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);

        ResponseEntity<Restaurant[]> restaurantResponse = this.client.
                exchange(path,HttpMethod.GET, new HttpEntity(headers), Restaurant[].class);

        if (restaurantResponse.getStatusCode() == HttpStatus.OK && restaurantResponse.getBody() != null){
            return List.of(restaurantResponse.getBody());
        } else {
            return new ArrayList<>();
        }
    }
}

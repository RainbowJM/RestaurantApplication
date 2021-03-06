package com.restaurant.TableService.config;

import com.restaurant.TableService.adapter.outgoing.rest.RestaurantRestRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${restaurant.rest.restaurant-path}")
    private String restaurantEndpointPath;

    @Value("${restaurant.rest.private-token}")
    public String privateToken;

    @Bean
    public RestaurantRestRepository httpRestaurantRepository() {
        return new RestaurantRestRepository(privateToken, restaurantEndpointPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

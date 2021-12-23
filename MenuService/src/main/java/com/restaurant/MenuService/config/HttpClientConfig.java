package com.restaurant.MenuService.config;

import com.restaurant.MenuService.adapters.outgoing.rest.RestaurantRestRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${restaurant.rest.user-path}")
    private String userEndpointPath;

    @Value("${restaurant.rest.restaurant-path}")
    private String restaurantEndpointPath;

    @Value("${restaurant.rest.private-token}")
    private String privateToken;

    @Bean
    public RestaurantRestRepository httpRestaurantRepository(RestTemplate restTemplate) {
        return new RestaurantRestRepository(privateToken, restaurantEndpointPath, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

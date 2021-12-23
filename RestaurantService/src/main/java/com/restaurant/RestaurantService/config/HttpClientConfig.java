package com.restaurant.RestaurantService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${restaurant.rest.menu-path}")
    private String menuEndpointPath;
    @Value("${restaurant.rest.order-path}")
    private String orderEndpointPath;
    @Value("${restaurant.rest.table-path}")
    private String tableEndpointPath;
    @Value("${restaurant.rest.restaurant-path}")
    private String restaurantEndpointPath;
    @Value("${restaurant.rest.user-path}")
    private String userEndpointPath;

    @Value("${restaurant.rest.private-token}")
    public String privateToken;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

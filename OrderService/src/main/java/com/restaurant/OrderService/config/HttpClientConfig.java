package com.restaurant.OrderService.config;

import com.restaurant.OrderService.adapters.incoming.message.UserEventListener;
import com.restaurant.OrderService.adapters.outgoing.rest.MenuRestRepository;
import com.restaurant.OrderService.adapters.outgoing.rest.RestaurantRestRepository;
import com.restaurant.OrderService.adapters.outgoing.rest.TableRestRepository;
import com.restaurant.OrderService.adapters.outgoing.rest.UserRestRepository;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.port.UserRepository;
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

    @Value("${restaurant.rest.table-path}")
    private String tableEndpointPath;

    @Value("${restaurant.rest.menu-path}")
    private String menuEndpointPath;

    @Value("${restaurant.rest.private-token}")
    private String privateToken;

    @Bean
    public UserRestRepository httpUserRepository(RestTemplate restTemplate) {
        return new UserRestRepository(privateToken, userEndpointPath, restTemplate);
    }

    @Bean
    public RestaurantRestRepository httpRestaurantRepository(RestTemplate restTemplate) {
        return new RestaurantRestRepository(privateToken, restaurantEndpointPath, restTemplate);
    }

    @Bean
    public TableRestRepository httpTableRepository(RestTemplate restTemplate) {
        return new TableRestRepository(privateToken, tableEndpointPath, restTemplate);
    }

    @Bean
    public MenuRestRepository httpMenuRepository(RestTemplate restTemplate) {
        return new MenuRestRepository(privateToken, menuEndpointPath, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

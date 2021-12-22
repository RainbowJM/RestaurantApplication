package com.restaurant.UserService.config;

import com.restaurant.UserService.adapter.outgoing.rest.OrderRestRepository;
import com.restaurant.UserService.adapter.outgoing.rest.TableRestRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${restaurant.rest.table-path}")
    private String tableEndpointPath;

    @Value("${restaurant.rest.order-path}")
    private String orderEndpointPath;

    @Value("${restaurant.rest.private-token}")
    public static String privateToken;

    @Bean
    public TableRestRepository httpTableRepository() {
        return new TableRestRepository(privateToken, tableEndpointPath, restTemplate());
    }

    @Bean
    public OrderRestRepository httpOrderRepository() {
        return new OrderRestRepository(privateToken, orderEndpointPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

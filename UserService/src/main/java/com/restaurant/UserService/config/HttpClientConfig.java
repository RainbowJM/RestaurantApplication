package com.restaurant.UserService.config;

import com.restaurant.UserService.adapter.outgoing.rest.TableRestRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${restaurant.rest.table-path}")
    private String tableEndpointPath;

    @Bean
    public TableRestRepository httpJobRepository() {
        return new TableRestRepository(tableEndpointPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

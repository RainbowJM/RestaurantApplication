package com.restaurant.OrderService.adapters.outgoing.rest;

import com.restaurant.OrderService.core.domain.external.User;
import com.restaurant.OrderService.core.port.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserRestRepository implements UserRepository {
    private final String privateToken;
    private final String userEndpointPath;
    private final RestTemplate client;

    @Override
    public List<User> getAllUsers() {
        URI path = URI.create(this.userEndpointPath + "/user/");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);

        ResponseEntity<User[]> ordersResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), User[].class);

        if (ordersResponse.getStatusCode() == HttpStatus.OK && ordersResponse.getBody() != null) {
            return List.of(ordersResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

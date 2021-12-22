package com.restaurant.UserService.adapter.outgoing.rest;

import com.restaurant.UserService.core.port.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OrderRestRepository implements OrderRepository {
    private final String privateToken;
    private final String orderEndpointPath;
    private final RestTemplate client;

    @Override
    public List<OrderResult> getAllOrdersFromUser(String id) {
        URI path = URI.create(this.orderEndpointPath + "/order/?user="); // todo: add query filters
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);
        ResponseEntity<OrderResult[]> ordersResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), OrderResult[].class);

        if (ordersResponse.getStatusCode() == HttpStatus.OK && ordersResponse.getBody() != null) {
            return List.of(ordersResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

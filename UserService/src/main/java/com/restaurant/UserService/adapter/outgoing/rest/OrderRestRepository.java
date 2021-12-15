package com.restaurant.UserService.adapter.outgoing.rest;

import com.restaurant.UserService.adapter.outgoing.message.OrderResult;
import com.restaurant.UserService.core.port.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class OrderRestRepository implements OrderRepository {
    private final String orderEndpointPath;
    private final RestTemplate client;

    @Override
    public List<OrderResult> getAllOrdersFromUser(String id) {
        URI path = URI.create(this.orderEndpointPath + "/order/?user="); // todo: add query filters
        OrderResult[] results = this.client.getForObject(path, OrderResult[].class);
        if (results == null)
            return new ArrayList<OrderResult>();

        return Arrays.stream(results).toList();
    }
}

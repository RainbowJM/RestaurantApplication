package com.restaurant.OrderService.adapters.outgoing.rest;

import com.restaurant.OrderService.core.domain.external.Table;
import com.restaurant.OrderService.core.port.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TableRestRepository implements TableRepository {
    private final String privateToken;
    private final String userEndpointPath;
    private final RestTemplate client;

    @Override
    public List<Table> getAllTables(){
        URI path = URI.create(this.userEndpointPath + "/table/");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);

        ResponseEntity<Table[]> ordersResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), Table[].class);

        if(ordersResponse.getStatusCode() == HttpStatus.OK && ordersResponse.getBody() != null)
            return List.of(ordersResponse.getBody());
        else return new ArrayList<>();
    }
}

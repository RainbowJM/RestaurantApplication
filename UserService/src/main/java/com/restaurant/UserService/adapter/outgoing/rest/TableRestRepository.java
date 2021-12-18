package com.restaurant.UserService.adapter.outgoing.rest;

import com.restaurant.UserService.adapter.outgoing.message.OrderResult;
import com.restaurant.UserService.adapter.outgoing.message.TableResult;
import com.restaurant.UserService.core.port.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class TableRestRepository implements TableRepository {
    private final String privateToken;
    private final String tableEndpointPath;
    private final RestTemplate client;

    @Override
    public List<TableResult> getAllTablesFromUser(String id) {
        URI path = URI.create(this.tableEndpointPath + "/table/?user="); // todo: add query filters
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);
        ResponseEntity<TableResult[]> tablesResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), TableResult[].class);

        if (tablesResponse.getStatusCode() == HttpStatus.OK && tablesResponse.getBody() != null) {
            return List.of(tablesResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

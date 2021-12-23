package com.restaurant.UserService.adapter.outgoing.rest;

import com.restaurant.UserService.core.domain.external.Table;
import com.restaurant.UserService.core.port.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TableRestRepository implements TableRepository {
    private final String privateToken;
    private final String tableEndpointPath;
    private final RestTemplate client;

    @Override
    public List<Table> getAllTablesFromUser(String id) {
        URI path = URI.create(this.tableEndpointPath + "/table/?user="); // todo: add query filters
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);
        ResponseEntity<Table[]> tablesResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), Table[].class);

        if (tablesResponse.getStatusCode() == HttpStatus.OK && tablesResponse.getBody() != null) {
            return List.of(tablesResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

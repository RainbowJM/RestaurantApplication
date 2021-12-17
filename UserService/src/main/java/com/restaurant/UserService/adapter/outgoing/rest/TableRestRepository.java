package com.restaurant.UserService.adapter.outgoing.rest;

import com.restaurant.UserService.adapter.outgoing.message.TableResult;
import com.restaurant.UserService.core.port.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class TableRestRepository implements TableRepository {
    private final String tableEndpointPath;
    private final RestTemplate client;

    @Override
    public List<TableResult> getAllTablesFromUser(String id) {
        URI path = URI.create(this.tableEndpointPath + "/table/?user="); // todo: add query filters
        TableResult[] results = this.client.getForObject(path, TableResult[].class);

        if (results == null)
            return new ArrayList<TableResult>();

        return Arrays.stream(results).toList();
    }
}

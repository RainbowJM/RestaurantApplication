package com.restaurant.OrderService.adapters.outgoing.rest;

import com.restaurant.OrderService.core.domain.external.Menu;
import com.restaurant.OrderService.core.domain.external.Restaurant;
import com.restaurant.OrderService.core.port.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MenuRestRepository implements MenuRepository {
    private final String privateToken;
    private final String menuEndpointPath;
    private final RestTemplate client;

    @Override
    public List<Menu> getAllMenus() {
        URI path = URI.create(this.menuEndpointPath + "/menu/");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(privateToken);

        ResponseEntity<Menu[]> menusResponse = this.client.exchange(path, HttpMethod.GET, new HttpEntity(headers), Menu[].class);

        if (menusResponse.getStatusCode() == HttpStatus.OK && menusResponse.getBody() != null) {
            return List.of(menusResponse.getBody());
        }
        else {
            return new ArrayList<>();
        }
    }
}

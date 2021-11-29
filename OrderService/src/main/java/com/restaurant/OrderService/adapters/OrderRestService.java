package com.restaurant.OrderService.adapters;

import com.restaurant.OrderService.application.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderRestService {
    public final OrderService service;

    public OrderRestService(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public String placeholder() {
        // Everything should be done in the application domain. Adapters shouldn't have any dependencies on the domain package.
        return service.placeholder();
    }
}

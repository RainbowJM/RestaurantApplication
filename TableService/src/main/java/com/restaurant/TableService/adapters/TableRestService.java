package com.restaurant.TableService.adapters;

import com.restaurant.TableService.application.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableRestService {
    public final TableService service;

    public TableRestService(TableService service) {
        this.service = service;
    }

    @GetMapping
    public String placeholder() {
        // Everything should be done in the application domain. Adapters shouldn't have any dependencies on the domain package.
        return service.placeholder();
    }
}

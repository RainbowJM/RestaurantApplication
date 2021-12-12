package com.restaurant.MenuService.adapters;

import com.restaurant.MenuService.application.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu/")
public class MenuRestService {
    private final MenuService service;

    public MenuRestService(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public String placeholder() {
        // Everything should be done in the application domain. Adapters shouldn't have any dependencies on the domain package.
        return service.placeholder();
    }

    @GetMapping("{restaurantId}/")
    public List<>
}

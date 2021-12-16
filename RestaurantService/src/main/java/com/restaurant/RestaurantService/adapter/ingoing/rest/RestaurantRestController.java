package com.restaurant.RestaurantService.adapter.ingoing.rest;

import com.restaurant.RestaurantService.adapter.ingoing.rest.requestDTO.CreateRestaurantRequest;
import com.restaurant.RestaurantService.core.application.RestaurantCommandService;
import com.restaurant.RestaurantService.core.application.RestaurantQueryService;
import com.restaurant.RestaurantService.core.application.command.CreateRestaurantCommand;
import com.restaurant.RestaurantService.core.application.command.DeleteRestaurantCommand;
import com.restaurant.RestaurantService.core.application.query.ListRestaurantsQuery;
import com.restaurant.RestaurantService.core.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path="/restaurant")
public class RestaurantRestController {
    public final RestaurantQueryService queryService;
    public final RestaurantCommandService commandService;

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getRestaurants() {
        return this.queryService.handle(new ListRestaurantsQuery());
    }

    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@Valid @RequestBody CreateRestaurantRequest createRequest) {
        return this.commandService.handle(new CreateRestaurantCommand(createRequest.name, createRequest.address));
    }

    @DeleteMapping(path="/{name}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable String name) {
        this.commandService.handle(new DeleteRestaurantCommand(name));
    }
}

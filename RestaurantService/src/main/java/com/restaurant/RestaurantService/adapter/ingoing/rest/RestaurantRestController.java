package com.restaurant.RestaurantService.adapter.ingoing.rest;

import com.restaurant.RestaurantService.adapter.ingoing.rest.requestDTO.CreateRestaurantRequest;
import com.restaurant.RestaurantService.core.application.RestaurantCommandService;
import com.restaurant.RestaurantService.core.application.RestaurantQueryService;
import com.restaurant.RestaurantService.core.application.command.CreateRestaurantCommand;
import com.restaurant.RestaurantService.core.application.command.DeleteRestaurantCommand;
import com.restaurant.RestaurantService.core.application.query.ListRestaurantsQuery;
import com.restaurant.RestaurantService.core.application.query.GetRestaurantQuery;
import com.restaurant.RestaurantService.core.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    @RolesAllowed({"Management", "OtherService"})
    public Restaurant createRestaurant(@Valid @RequestBody CreateRestaurantRequest createRequest) {
        return this.commandService.handle(new CreateRestaurantCommand(createRequest.name, createRequest.address));
    }

    @GetMapping(path="/{name}/")
    @ResponseStatus(HttpStatus.OK)
    public Restaurant getRestaurant(@PathVariable String name) {
        return this.queryService.handle(new GetRestaurantQuery(name));
    }

    @DeleteMapping(path="/{name}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Management", "OtherService"})
    public void deleteRestaurant(@PathVariable String name) {
        this.commandService.handle(new DeleteRestaurantCommand(name));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint or your login token has expired."));
    }
}

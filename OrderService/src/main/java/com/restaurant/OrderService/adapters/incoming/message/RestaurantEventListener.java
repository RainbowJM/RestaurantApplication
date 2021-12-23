package com.restaurant.OrderService.adapters.incoming.message;

import com.restaurant.OrderService.adapters.incoming.message.event.*;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.application.query.ListAllOrdersQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.external.Restaurant;
import com.restaurant.OrderService.core.domain.external.User;
import com.restaurant.OrderService.core.port.RestaurantRepository;
import com.restaurant.OrderService.core.port.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantEventListener {
    private final OrderQueryService queryService;
    private final OrderCommandService commandService;
    private final RestaurantRepository restaurantRepository;

    private static List<Restaurant> restaurants = new ArrayList<>();
    public static boolean initialized = false;

    public RestaurantEventListener(OrderQueryService queryService, OrderCommandService commandService, RestaurantRepository restaurantRepository) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.restaurantRepository = restaurantRepository;
    }

    @EventListener
    private void onReadyEvent(ContextRefreshedEvent event) {
        initializeRestaurants();
    }

    // Initialization is done at service initialization and when send a ready event from UserService, so that order of startup doesn't matter
    private void initializeRestaurants() {
        if (!initialized) {
            try {
                restaurants = new ArrayList<>(restaurantRepository.getAllRestaurants());
            }
            catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to UserService because it probably hasn't started yet. UserReadyEvent will need to be used to initialize this server.");
                return;
            }
            initialized = true;
        }
    }

    @RabbitListener(queues = "#{'${message.queue.restaurant-event}'}")
    private void listen(RestaurantEvent event) {
        switch (event.getEventKey()) {
            case RestaurantReadyEvent.KEY -> this.initializeRestaurants();
            case RestaurantCreatedEvent.KEY -> this.handle((RestaurantCreatedEvent)event);
            case RestaurantRemovedEvent.KEY -> this.handle((RestaurantRemovedEvent)event);
        }
    }

    private void handle(RestaurantCreatedEvent event) {
        restaurants.add(new Restaurant(event.getName(), event.getAddress()));
    }

    private void handle(RestaurantRemovedEvent event) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(event.getName())) {
                restaurants.remove(restaurant);
                break;
            }
        }
    }

    public static List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public static boolean restaurantExists(String nameId) {
        return restaurants.stream().anyMatch(restaurant -> restaurant.getName().equals(nameId));
    }
}

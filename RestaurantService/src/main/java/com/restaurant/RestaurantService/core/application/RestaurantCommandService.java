package com.restaurant.RestaurantService.core.application;

import com.restaurant.RestaurantService.adapter.outgoing.message.EventPublisher;
import com.restaurant.RestaurantService.core.application.command.CreateRestaurantCommand;
import com.restaurant.RestaurantService.core.application.command.DeleteRestaurantCommand;
import com.restaurant.RestaurantService.core.domain.Restaurant;
import com.restaurant.RestaurantService.core.domain.event.RestaurantCreatedEvent;
import com.restaurant.RestaurantService.core.domain.event.RestaurantRemovedEvent;
import com.restaurant.RestaurantService.core.port.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantCommandService {
    private final RestaurantRepository repository;
    private final EventPublisher eventPublisher;

    public RestaurantCommandService(RestaurantRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Restaurant handle(CreateRestaurantCommand createRestaurantCommand) {
        Restaurant createdRestaurant = repository.save(new Restaurant(createRestaurantCommand.name(), createRestaurantCommand.location()));
        if (createdRestaurant != null) {
            this.eventPublisher.publish(new RestaurantCreatedEvent(createdRestaurant.getName(), createdRestaurant.getAddress()));
        }
        return createdRestaurant;
    }

    public void handle(DeleteRestaurantCommand deleteRestaurantCommand) {
        Restaurant deletedRestaurant = this.repository.deleteByName(deleteRestaurantCommand.name());
        if (deletedRestaurant != null) {
            this.eventPublisher.publish(new RestaurantRemovedEvent(deletedRestaurant.getName()));
        }
    }
}

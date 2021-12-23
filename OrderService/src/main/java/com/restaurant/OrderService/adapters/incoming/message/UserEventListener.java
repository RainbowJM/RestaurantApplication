package com.restaurant.OrderService.adapters.incoming.message;

import com.restaurant.OrderService.adapters.incoming.message.event.*;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.application.query.ListOrdersQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.external.User;
import com.restaurant.OrderService.core.port.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEventListener {
    private final OrderQueryService queryService;
    private final OrderCommandService commandService;
    private final UserRepository userRepository;

    private static List<User> users = new ArrayList<>();
    public static boolean initialized = false;

    public UserEventListener(OrderQueryService queryService, OrderCommandService commandService, UserRepository userRepository) {
        this.queryService = queryService;
        this.commandService = commandService;
        this.userRepository = userRepository;
    }

    @EventListener
    private void onReadyEvent(ContextRefreshedEvent event) {
        initializeUsers();
    }

    // Initialization is done at service initialization and when send a ready event from UserService, so that order of startup doesn't matter
    private void initializeUsers() {
        if (!initialized) {
            try {
                users = new ArrayList<>(userRepository.getAllUsers());
            }
            catch (ResourceAccessException exception) {
                System.out.println("Failed to connect to UserService because it probably hasn't started yet. UserReadyEvent will need to be used to initialize this server.");
                return;
            }
            initialized = true;
        }
    }

    @RabbitListener(queues = "#{'${message.queue.user-event}'}")
    private void listen(UserEvent event) {
        switch (event.getEventKey()) {
            case UserReadyEvent.KEY -> this.initializeUsers();
            case UserChangedEvent.KEY -> this.handle((UserChangedEvent) event);
            case UserRegisteredEvent.KEY -> this.handle((UserRegisteredEvent) event);
            case UserRemovedEvent.KEY -> this.handle((UserRemovedEvent) event);
        }
    }

    private void handle(UserRemovedEvent event) {
        for (User user : users) {
            if (user.getUsername().equals(event.getUsername())) {
                users.remove(user);
                break;
            }
        }

        System.out.println(String.format("Deleting orders from this user %s because a user deletion event was received", event.getUsername()));

        // todo: maybe implement delete command could use a delete query to delete from certain users
        for (Order order : queryService.handle(new ListOrdersQuery())) {
            if (order.getCustomerId().equals(event.getUsername())) {
                this.commandService.handle(new DeleteOrderCommand(order.getId()));
            }
        }
    }

    private void handle(UserRegisteredEvent event) {
        users.add(new User(event.getUsername(), event.getRole(), event.getFirstName(), event.getLastName()));
    }

    public void handle(UserChangedEvent event) {
        for (User user : users) {
            if (user.getUsername().equals(event.getUsername())) {
                user.setRole(event.getRole());
                user.setFirstName(event.getFirstName());
                user.setLastName(event.getLastName());
                return;
            }
        }
    }

    public static List<User> getUsers() {
        return users;
    }

    public static boolean userExists(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}

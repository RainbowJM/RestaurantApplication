package com.restaurant.OrderService.adapters.incoming.message;

import com.restaurant.OrderService.adapters.incoming.message.event.UserChangedEvent;
import com.restaurant.OrderService.adapters.incoming.message.event.UserEvent;
import com.restaurant.OrderService.adapters.incoming.message.event.UserRegisteredEvent;
import com.restaurant.OrderService.adapters.incoming.message.event.UserRemovedEvent;
import com.restaurant.OrderService.core.application.OrderCommandService;
import com.restaurant.OrderService.core.application.OrderQueryService;
import com.restaurant.OrderService.core.application.command.DeleteOrderCommand;
import com.restaurant.OrderService.core.application.query.ListAllOrdersQuery;
import com.restaurant.OrderService.core.domain.Order;
import com.restaurant.OrderService.core.domain.external.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserEventListener {
    private final OrderQueryService queryService;
    private final OrderCommandService commandService;
    private final List<User> users = new ArrayList<>();

    public UserEventListener(OrderQueryService queryService, OrderCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @PostConstruct
    private void initializeUsers() {
        // fixme: get existing users on initialization
    }

    @RabbitListener(queues = "#{'${message.queue.user-event}'}")
    private void listen(UserEvent event) {
        switch (event.getEventKey()) {
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
        for (Order order : queryService.handle(new ListAllOrdersQuery())) {
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
}

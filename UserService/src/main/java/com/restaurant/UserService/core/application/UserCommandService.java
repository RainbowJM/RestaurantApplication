package com.restaurant.UserService.core.application;

import com.restaurant.UserService.adapter.outgoing.message.EventPublisher;
import com.restaurant.UserService.core.domain.event.UserChangedEvent;
import com.restaurant.UserService.core.domain.event.UserReadyEvent;
import com.restaurant.UserService.core.domain.event.UserRegisteredEvent;
import com.restaurant.UserService.core.domain.event.UserRemovedEvent;
import com.restaurant.UserService.core.domain.external.Order;
import com.restaurant.UserService.core.application.command.ChangeUserCommand;
import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.core.application.command.RegisterUserCommand;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.exception.FailedToDeleteUser;
import com.restaurant.UserService.core.domain.exception.UserCannotBeFound;
import com.restaurant.UserService.core.domain.exception.UserDeleteWithActiveOrders;
import com.restaurant.UserService.core.domain.exception.UsernameAlreadyTaken;
import com.restaurant.UserService.core.port.OrderRepository;
import com.restaurant.UserService.core.port.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final EventPublisher eventPublisher;

    public UserCommandService(UserRepository userRepository, OrderRepository orderRepository, EventPublisher event) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.eventPublisher = event;
    }

    public User handle(RegisterUserCommand registerCommand) {
        if (userRepository.existsByUsername(registerCommand.username())) {
            throw new UsernameAlreadyTaken(registerCommand.username());
        }

        // todo: check if user had any tables and if so prevent it from being deleted
        User newUser = this.userRepository.save(new User(registerCommand.username(), registerCommand.password(), registerCommand.firstName(), registerCommand.lastName()));
        eventPublisher.publish(new UserRegisteredEvent(newUser.getUsername(), newUser.getRole().name(), newUser.getFirstName(), newUser.getLastName()));
        return newUser;
    }

    public void handle(DeleteUserCommand deleteCommand) {
        List<Order> orders = orderRepository.getAllOrdersFromUser(deleteCommand.username());
        boolean activeOrders = false;
        for (Order order : orders) {
            // fixme: This should probably be using an enum
            if (order.status().equals("Active")) {
                activeOrders = true;
                break;
            }
        }

        if (activeOrders) {
            throw new UserDeleteWithActiveOrders(deleteCommand.username());
        }

        // fixme: send event over message queues
        if (this.userRepository.deleteByUsername(deleteCommand.username()) != null) {
            throw new FailedToDeleteUser(deleteCommand.username());
        }
        eventPublisher.publish(new UserRemovedEvent(deleteCommand.username()));
    }

    public User handle(ChangeUserCommand changeCommand) {
        User user = this.userRepository.findUserByUsername(changeCommand.username());
        if (user == null)
            throw new UserCannotBeFound(changeCommand.username());

        if (changeCommand.password() != null) user.setPassword(changeCommand.password());
        if (changeCommand.role() != null) user.setRole(changeCommand.role());
        if (changeCommand.firstName() != null) user.setFirstName(changeCommand.firstName());
        if (changeCommand.password() != null) user.setLastName(changeCommand.lastName());
        User updatedUser = this.userRepository.save(user);
        eventPublisher.publish(new UserChangedEvent(updatedUser.getUsername(), updatedUser.getRole().name(), updatedUser.getFirstName(), updatedUser.getLastName()));
        return updatedUser;
    }

    @EventListener
    public void sendUserReadyEvent(ApplicationReadyEvent event) {
        eventPublisher.publish(new UserReadyEvent());
    }
}

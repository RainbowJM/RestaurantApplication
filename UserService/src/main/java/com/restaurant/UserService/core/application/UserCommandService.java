package com.restaurant.UserService.core.application;

import com.restaurant.UserService.adapter.outgoing.message.OrderResult;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserCommandService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserCommandService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public User handle(RegisterUserCommand registerCommand) {
        if (userRepository.existsByUsername(registerCommand.username())) {
            throw new UsernameAlreadyTaken(registerCommand.username());
        }

        User newUser = new User(registerCommand.username(), registerCommand.password(), registerCommand.firstName(), registerCommand.lastName());

        // todo: publish events when a new user has been registered
        // todo: check if user had any tables and if so prevent it from being deleted
        return this.userRepository.save(newUser);
    }

    public void handle(DeleteUserCommand deleteCommand) {
        List<OrderResult> orders = orderRepository.getAllOrdersFromUser(deleteCommand.username());
        boolean activeOrders = false;
        for (OrderResult order : orders) {
            // fixme: This should probably be using an enum
            if (order.status().equals("Active")) {
                activeOrders = true;
            }
        }

        if (activeOrders) {
            throw new UserDeleteWithActiveOrders(deleteCommand.username());
        }

        // fixme: delete all orders from this user
        // fixme: send event over message queues

        if (this.userRepository.deleteByUsername(deleteCommand.username()).isEmpty()) {
            throw new FailedToDeleteUser(deleteCommand.username());
        }
    }

    public User handle(ChangeUserCommand changeCommand) {
        User user = this.userRepository.findUserByUsername(changeCommand.username());
        if (user == null)
            throw new UserCannotBeFound(changeCommand.username());

        if (changeCommand.password() != null) user.setPassword(changeCommand.password());
        if (changeCommand.role() != null) user.setRole(changeCommand.role());
        if (changeCommand.firstName() != null) user.setFirstName(changeCommand.firstName());
        if (changeCommand.password() != null) user.setLastName(changeCommand.lastName());
        return this.userRepository.save(user);
    }
}

package com.restaurant.UserService.core.application;

import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.core.application.command.RegisterUserCommand;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.exception.FailedToDeleteUser;
import com.restaurant.UserService.core.domain.exception.UsernameAlreadyTaken;
import com.restaurant.UserService.core.port.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCommandService {
    private final UserRepository repository;

    public UserCommandService(UserRepository repository) {
        this.repository = repository;
    }

    public User handle(RegisterUserCommand registerCommand) {
        if (repository.existsByUsername(registerCommand.username())) {
            throw new UsernameAlreadyTaken(registerCommand.username());
        }

        User newUser = new User(registerCommand.username(), registerCommand.password(), registerCommand.firstName(), registerCommand.lastName());

        // todo: publish events when a new user has been registered
        // todo: check if user had any tables and if so prevent it from being deleted
        return this.repository.save(newUser);
    }

    public void handle(DeleteUserCommand deleteCommand) {
        if (this.repository.deleteByUsername(deleteCommand.username()).isEmpty()) {
            throw new FailedToDeleteUser(deleteCommand.username());
        }
    }
}

package com.restaurant.UserService.adapter.ingoing.rest;

import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.RegisterUserRequest;
import com.restaurant.UserService.core.application.UserCommandService;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.core.application.command.RegisterUserCommand;
import com.restaurant.UserService.core.application.query.ListAllUsersQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.exception.FailedToDeleteUser;
import com.restaurant.UserService.core.domain.exception.UsernameAlreadyTaken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path="/user")
public class UserRestController {
    public final UserQueryService queryService;
    public final UserCommandService commandService;

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return this.queryService.handle(new ListAllUsersQuery());
    }

    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@Valid @RequestBody RegisterUserRequest registerRequest) {
        return this.commandService.handle(new RegisterUserCommand(registerRequest.username, registerRequest.password, registerRequest.firstName, registerRequest.lastName));
    }

    @DeleteMapping(path="/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        this.commandService.handle(new DeleteUserCommand(id));
    }

    @ExceptionHandler({FailedToDeleteUser.class})
    public ResponseEntity<Map<String, String>> handleForbiddenType(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", exception.getMessage()));
    }

    @ExceptionHandler({UsernameAlreadyTaken.class})
    public ResponseEntity<Map<String, String>> handleConflictType(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", exception.getMessage()));
    }
}

package com.restaurant.UserService.adapter.ingoing.rest;

import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.RegisterUserRequest;
import com.restaurant.UserService.core.application.UserCommandService;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.core.application.command.RegisterUserCommand;
import com.restaurant.UserService.core.application.query.ListAllUsersQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.exception.CantDeleteOtherUser;
import com.restaurant.UserService.core.domain.exception.FailedToDeleteUser;
import com.restaurant.UserService.core.domain.exception.UserDeleteWithActiveOrders;
import com.restaurant.UserService.core.domain.exception.UsernameAlreadyTaken;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path="/user")
public class UserRestController {
    private final UserQueryService queryService;
    private final UserCommandService commandService;

    @GetMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public List<User> getUsers() {
        return this.queryService.handle(new ListAllUsersQuery());
    }

    @PostMapping(path="/")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@Valid @RequestBody RegisterUserRequest registerRequest) {
        return this.commandService.handle(new RegisterUserCommand(registerRequest.username, registerRequest.password, registerRequest.firstName, registerRequest.lastName));
    }

    @DeleteMapping(path="/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public void deleteOwnAccount(Authentication auth) {
        this.commandService.handle(new DeleteUserCommand(auth.getName()));
    }

    @DeleteMapping(path="/{username}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public void deleteOtherUser(Authentication auth, @PathVariable String username) {
        this.commandService.handle(new DeleteUserCommand(username));
    }

    @GetMapping(path="/{username}/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"Staff", "Management", "OtherService"})
    public List<User> getUserName(@PathVariable String username) {
        return this.queryService.handle(new ListAllUsersQuery());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleOldTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You should make sure that your login token is still up-to-date."));
    }

    @ExceptionHandler({FailedToDeleteUser.class, UserDeleteWithActiveOrders.class})
    public ResponseEntity<Map<String, String>> handleForbiddenType(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", exception.getMessage()));
    }

    @ExceptionHandler({UsernameAlreadyTaken.class})
    public ResponseEntity<Map<String, String>> handleConflictType(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", exception.getMessage()));
    }
}

package com.restaurant.UserService.adapter.ingoing.rest;

import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.ChangeOtherUserRequest;
import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.ChangeUserRequest;
import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.RegisterUserRequest;
import com.restaurant.UserService.core.application.UserCommandService;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.application.command.ChangeUserCommand;
import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.core.application.command.RegisterUserCommand;
import com.restaurant.UserService.core.application.query.GetUserQuery;
import com.restaurant.UserService.core.application.query.ListAllUsersQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.UserRole;
import com.restaurant.UserService.core.domain.exception.FailedToDeleteUser;
import com.restaurant.UserService.core.domain.exception.UserDeleteWithActiveOrders;
import com.restaurant.UserService.core.domain.exception.UsernameAlreadyTaken;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    @RolesAllowed({"Management", "OtherService"})
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
    @RolesAllowed({"User", "Staff", "Management"})
    public void deleteOwnAccount(Authentication auth) {
        this.commandService.handle(new DeleteUserCommand(auth.getName()));
    }

    @DeleteMapping(path="/{username}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed({"Management", "OtherService"})
    public void deleteOtherUser(Authentication auth, @PathVariable String username) {
        this.commandService.handle(new DeleteUserCommand(username));
    }

    @GetMapping(path="/{username}/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"Management", "OtherService"})
    public User getUser(@PathVariable String username) {
        return this.queryService.handle(new GetUserQuery(username));
    }

    @PutMapping(path="/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"User", "Staff", "Management", "OtherService"})
    public User changeOwnAccount(Authentication auth, @Valid @RequestBody ChangeUserRequest changeRequest) {
        return this.commandService.handle(new ChangeUserCommand(auth.getName(), changeRequest.password, null, changeRequest.firstName, changeRequest.lastName));
    }

    @PutMapping(path="/{username}/")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({"Management", "OtherService"})
    public User changeAccount(Authentication auth, @PathVariable String username, @Valid @RequestBody ChangeOtherUserRequest changeRequest) {
        if (changeRequest.role == UserRole.OtherService) throw new RuntimeException("Can't upgrade your role to OtherService since it's reserved for internal services.");
        return this.commandService.handle(new ChangeUserCommand(username, changeRequest.password, changeRequest.role, changeRequest.firstName, changeRequest.lastName));
    }

    // fixme: Have an old token throw a proper exception
    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Map<String, String>> handleOldTokenException(ExpiredJwtException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied because your login has expired."));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleBadTokenException(Exception exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("error", "Access was denied! You probably don't have the necessary privileges to call this endpoint."));
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

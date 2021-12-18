package com.restaurant.UserService.adapter.ingoing.rest;

import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.LoginRequest;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.application.query.GetUserQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.UserRole;
import com.restaurant.UserService.adapter.ingoing.rest.respondDTO.LoginTokenResponse;
import com.restaurant.UserService.core.domain.exception.UserCannotBeFound;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Key;
import java.util.Calendar;

@RestController
public class LoginRestController {
    @Value("${restaurant.rest.signing-key}")
    private String signingKey;

    private final UserQueryService queryService;

    public LoginRestController(UserQueryService queryService) {
        this.queryService = queryService;
    }

    public String generateToken(String username, UserRole role) throws JwtException {
        Calendar tokenExpirationTime = Calendar.getInstance();
        tokenExpirationTime.add(Calendar.MINUTE, 60);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(tokenExpirationTime.getTime())
                .claim("role", role.toString())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    @PostMapping(path="/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginTokenResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = this.queryService.handle(new GetUserQuery(loginRequest.username));
        if (user == null)
            throw new UserCannotBeFound(loginRequest.username);
        return new LoginTokenResponse(loginRequest.username, generateToken(user.getUsername(), user.getRole()));
    }
}

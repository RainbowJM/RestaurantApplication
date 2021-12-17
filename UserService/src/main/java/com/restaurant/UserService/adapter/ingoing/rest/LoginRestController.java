package com.restaurant.UserService.adapter.ingoing.rest;

import com.restaurant.UserService.adapter.ingoing.rest.requestDTO.LoginRequest;
import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.domain.UserRole;
import com.restaurant.UserService.adapter.ingoing.rest.respondDTO.LoginTokenResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Key;
import java.util.Calendar;

@AllArgsConstructor
@RestController
public class LoginRestController {
    private final static Key key = MacProvider.generateKey();
    private final UserQueryService queryService;

    public String generateToken(String username, UserRole role) throws JwtException {
        Calendar tokenExpirationTime = Calendar.getInstance();
        tokenExpirationTime.add(Calendar.MINUTE, 60);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(tokenExpirationTime.getTime())
                .claim("role", role.toString())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    @PostMapping(path="/login")
    public LoginTokenResponse loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return new LoginTokenResponse(loginRequest.username, generate);
    }
}

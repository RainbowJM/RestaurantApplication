package com.restaurant.UserService.adapter.ingoing.rest.respondDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginTokenResponse {
    private String username;
    private String token;
}

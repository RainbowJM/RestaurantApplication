package com.restaurant.UserService.adapter.ingoing.rest.requestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank(message="Username can't be empty")
    @Email
    public String username;
    @NotBlank(message="Password can't be empty")
    public String password;
}

package com.restaurant.UserService.adapter.ingoing.rest.requestDTO;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserRequest {
    @NotBlank(message="Username can't be empty")
    @Size(min=5, max=25, message="Your username has to be at least 5 characters and can't be longer then 25 characters")
    @Email
    public String username;
    @NotBlank(message="Password can't be empty")
    @Size(min=5, max=25, message="Your password has to be at least 5 characters and can't be longer then 25 characters")
    public String password;
    @NotBlank(message="You need to provide a first name")
    public String firstName;
    @NotBlank(message="You need to provide a last name")
    public String lastName;
}

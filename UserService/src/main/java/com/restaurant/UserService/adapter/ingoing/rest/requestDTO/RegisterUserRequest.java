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
    @Size(min=1, max=50, message="You need to use a first name that's at least 1 character and a maximum of 50 characters long")
    public String firstName;
    @Size(min=1, max=50, message="You need to use a last name that's at least 1 character and a maximum of 50 characters long")
    @NotBlank(message="You need to provide a last name")
    public String lastName;
}

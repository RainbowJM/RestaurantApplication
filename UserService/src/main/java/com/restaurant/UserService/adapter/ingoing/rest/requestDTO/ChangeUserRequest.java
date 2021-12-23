package com.restaurant.UserService.adapter.ingoing.rest.requestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangeUserRequest {
    @Size(min=5, max=25, message="Your password has to be at least 5 characters and can't be longer then 25 characters")
    public String password;
    @Size(min=1, max=50, message="You need to use a first name that's at least 1 character and a maximum of 50 characters long")
    public String firstName;
    @Size(min=1, max=50, message="You need to use a first name that's at least 1 character and a maximum of 50 characters long")
    public String lastName;
}

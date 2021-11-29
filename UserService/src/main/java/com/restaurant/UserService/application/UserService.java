package com.restaurant.UserService.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    public UserService() {
    }

    public String placeholder() {
        // Try to have as much logic happen in the domain model
        return "This endpoint is working!";
    }
}

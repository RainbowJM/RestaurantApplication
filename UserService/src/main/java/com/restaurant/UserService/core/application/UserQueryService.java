package com.restaurant.UserService.core.application;

import com.restaurant.UserService.core.application.query.ListAllUsersQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.port.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserQueryService {
    private final UserRepository repository;

    public UserQueryService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> handle(ListAllUsersQuery listQuery) {
        return this.repository.findAll();
    }
}

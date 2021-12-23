package com.restaurant.UserService.core.application;

import com.restaurant.UserService.core.application.query.GetUserQuery;
import com.restaurant.UserService.core.application.query.ListAllUsersQuery;
import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.UserRole;
import com.restaurant.UserService.core.port.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public User handle(GetUserQuery getUserQuery) {
        return this.repository.findUserByUsername(getUserQuery.username());
    }
}

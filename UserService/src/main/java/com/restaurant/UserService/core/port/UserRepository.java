package com.restaurant.UserService.core.port;

import com.restaurant.UserService.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    Optional<User> findById(String id);
    boolean existsById(String id);
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
    User findUserByUsername(String username);
    Optional<User> deleteByUsername(String username);
}

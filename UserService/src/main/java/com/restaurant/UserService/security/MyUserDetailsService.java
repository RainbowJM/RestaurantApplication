package com.restaurant.UserService.security;

import com.restaurant.UserService.core.domain.User;
import com.restaurant.UserService.core.domain.UserRole;
import com.restaurant.UserService.core.port.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public MyUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optUser = repository.findByUsername(email);
        if (optUser.isEmpty()) {
            return new org.springframework.security.core.userdetails.User("", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_"+UserRole.Guest)));
        }
        else {
            User user = optUser.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole())));
        }
    }
}

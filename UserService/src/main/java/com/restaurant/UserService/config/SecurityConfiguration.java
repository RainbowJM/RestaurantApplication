package com.restaurant.UserService.config;

import com.restaurant.UserService.core.application.UserQueryService;
import com.restaurant.UserService.core.port.UserRepository;
import com.restaurant.UserService.security.MyUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserQueryService queryService;
    private final UserRepository userRepository;
    private final UserDetailsService userService;

    public SecurityConfiguration(UserQueryService queryService, UserRepository userRepository, MyUserDetailsService userService) {
        this.queryService = queryService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
        httpSecurity.cors().and().csrf().disable();
    }
}

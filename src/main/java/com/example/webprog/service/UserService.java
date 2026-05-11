package com.example.webprog.service;

import com.example.webprog.model.User;
import com.example.webprog.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String username,
                         String password,
                         String email) {

        repository.save(
                new User(
                        username,
                        passwordEncoder.encode(password),
                        email,
                        Set.of("ROLE_USER")
                )
        );
    }

    public void createUser(String username,
                           String password,
                           String email,
                           String role) {

        repository.save(
                new User(
                        username,
                        passwordEncoder.encode(password),
                        email,
                        Set.of(role)
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(r -> r.replace("ROLE_", ""))
                                .toArray(String[]::new)
                )
                .build();
    }
}

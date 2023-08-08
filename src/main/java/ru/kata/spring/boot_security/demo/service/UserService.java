package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> usersList();

    Optional<User> getUser(Long id);

    void adduser(User user);

    void delete(Long id);

    void update(User user);
    User findByUsername (String username);
}

package ru.kata.spring.boot_security.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInitializer {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DbInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void initUsers() {

        Role adminRole = new Role("ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        roleService.saveRole(adminRole);

        Role userRole = new Role("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        roleService.saveRole(userRole);

        User admin = new User("admin", "admin", (byte) 25, "admin@mail.ru", "password", adminRoles);

        userService.addUser(admin);

        User user = new User("user", "user", (byte) 30, "user@mail.ru", "password", userRoles);

        userService.addUser(user);
    }
}
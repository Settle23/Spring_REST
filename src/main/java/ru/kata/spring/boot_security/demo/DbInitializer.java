package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
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
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        roleService.saveRole(adminRole);

        User admin = new User("Admin", "User", (byte) 30, "admin@example.com",
                "admin", "password", adminRoles);
        userService.addUser(admin);

        Role userRole = new Role("ROLE_USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        roleService.saveRole(userRole);

        User regUser = new User("Regular", "User", (byte) 25, "user@example.com",
                "user", "password", userRoles);
        userService.addUser(regUser);
    }
}

package ru.vizgalin.SecurityApp.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.vizgalin.SecurityApp.models.Role;
import ru.vizgalin.SecurityApp.models.User;
import ru.vizgalin.SecurityApp.services.RoleService;
import ru.vizgalin.SecurityApp.services.UserService;

import java.util.Set;

@Component
public class testUsers implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public testUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleService.getAllRoles().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            roleService.saveRole(roleAdmin);
            roleService.saveRole(roleUser);
        }
        if (userService.findAllUsers().isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@mail.ru");
            admin.setPassword("admin");
            admin.setFirstName("John");
            admin.setLastName("Smith");
            admin.setAge(25);
            admin.setRoles(Set.of(roleService.findByName("ROLE_ADMIN"), roleService.findByName("ROLE_USER")));
            userService.saveUser(admin);

            User user = new User();
            user.setEmail("user@mail.ru");
            user.setPassword("user");
            user.setFirstName("Eddy");
            user.setLastName("Stone");
            user.setAge(18);
            user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
            userService.saveUser(user);
        }
    }
}
package ru.vizgalin.SecurityApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.vizgalin.SecurityApp.models.User;

import java.util.List;

public interface UserService {
    User findByUserName(String username);

    List<User> findAllUsers();

    User saveUser(User user);

    User updateUser(User updatedUser, String newPassword);

    void deleteUser(Long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findById(Long id);

    boolean isAdmin(User user);

    boolean hasRole(User user, String role);
}

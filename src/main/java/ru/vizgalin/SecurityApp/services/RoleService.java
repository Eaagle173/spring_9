package ru.vizgalin.SecurityApp.services;

import ru.vizgalin.SecurityApp.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role findByName(String name);

    void saveRole(Role role);
}
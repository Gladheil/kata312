package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role getRole(String roleName);
    void addRole(Role role);
    List<Role> getRolesByNames(List<String> names);
}

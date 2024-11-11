package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDAO {
    Role getRole(String role);
    void addRole(Role role);
    List<Role> getRolesByNames(List<String> names);
}

package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.Set;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    Set<User> getAll();
    User getUserById(int id);
    void deleteUserById(int id);
    User getUserByUsername(String username);
    int getIdByUsername(String username);
}

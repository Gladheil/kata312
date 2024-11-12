package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    Set<User> getUsers();
    User getUserById(int id);
    void deleteUserById(int id);
    User getUserByUsername(String username);
    int getIdByUsername(String username);
}

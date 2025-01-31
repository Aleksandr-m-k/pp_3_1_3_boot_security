package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
     User findByUsername(String username);

    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    User updateUser(User user);

    void deleteUser(int id);
}

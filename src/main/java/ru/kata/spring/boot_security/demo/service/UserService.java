package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void add(User user);

    void update(User user);

    void delete(long id);

    User findById(long id);

}

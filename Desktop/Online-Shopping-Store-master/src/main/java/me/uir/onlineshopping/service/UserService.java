package me.uir.onlineshopping.service;

import me.uir.onlineshopping.entity.User;

import java.util.Collection;

public interface UserService {
    User findOne(String email);
    Collection<User> findByRole(String role);
    void save(User user);
    void update(User user);
}

package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.entity.user.User;
import ru.romanov.marketplace.productservice.entity.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    List<User> findAll();

    List<User> findAllByRole(UserRole role);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User updateUser(Long userId, User updated);

    void deleteById(Long id);

}

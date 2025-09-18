package ru.romanov.marketplace.productservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.romanov.marketplace.productservice.entity.user.User;
import ru.romanov.marketplace.productservice.service.UserService;
import ru.romanov.marketplace.productservice.entity.user.UserRole;
import ru.romanov.marketplace.productservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(Long userId, User updated) {
        User user = findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("User with id %s not found", userId)));
        user.setUsername(updated.getUsername());
        user.setEmail(updated.getEmail());
        user.setPhone(updated.getPhone());
        user.setPassword(updated.getPassword());
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("User with id %s not found", id));
        }
        userRepository.deleteById(id);
    }
}

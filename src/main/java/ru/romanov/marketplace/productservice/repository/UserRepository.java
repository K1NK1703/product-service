package ru.romanov.marketplace.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanov.marketplace.productservice.entity.user.User;
import ru.romanov.marketplace.productservice.entity.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

}

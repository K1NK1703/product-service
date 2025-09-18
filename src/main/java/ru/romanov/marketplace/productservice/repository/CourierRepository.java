package ru.romanov.marketplace.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanov.marketplace.productservice.entity.user.Courier;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {

    Optional<Courier> findByUsername(String username);

    Optional<Courier> findByEmail(String email);

}

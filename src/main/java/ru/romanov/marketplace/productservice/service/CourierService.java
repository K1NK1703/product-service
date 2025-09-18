package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.entity.user.Courier;

import java.util.List;
import java.util.Optional;

public interface CourierService {

    Courier save(Courier courier);

    List<Courier> findAll();

    Optional<Courier> findById(Long id);

    Optional<Courier> findByUsername(String username);

    Optional<Courier> findByEmail(String email);

    Courier updateCourier(Long courierId, Courier updated);

    void deleteById(Long id);

}

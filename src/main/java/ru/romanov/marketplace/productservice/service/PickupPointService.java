package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.entity.pickup.PickupPoint;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;

import java.util.List;
import java.util.Optional;

public interface PickupPointService {

    PickupPoint save(PickupPoint pickupPoint);

    List<PickupPoint> findAll();

    Optional<PickupPoint> findByAddress(PointAddress address);

    void deleteByAddress(PointAddress address);

}

package ru.romanov.marketplace.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPoint;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
}

package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.entity.delivery.Delivery;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryStatus;
import ru.romanov.marketplace.productservice.entity.delivery.CourierDelivery;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryAddress;
import ru.romanov.marketplace.productservice.entity.delivery.PickupPointDelivery;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DeliveryService {

    Delivery save(Delivery delivery);

    List<Delivery> findAll();

    Optional<Delivery> findById(Long id);

    List<Delivery> findByStatus(DeliveryStatus status);

    List<CourierDelivery> findCourierDeliveriesByCourierId(Long courierId);

    List<PickupPointDelivery> findPickupDeliveriesByAddress(PointAddress address);

    List<CourierDelivery> findCourierDeliveriesByAddress(DeliveryAddress address);

    List<Delivery> findByDeliveryDate(LocalDate date);

    Delivery updateStatus(Long deliveryId, DeliveryStatus status);

    Delivery updateDeliveryTime(Long deliveryId, LocalDateTime deliveryTime);

    Delivery updateDeliveryCourier(Long deliveryId, Long courierId);

    void deleteById(Long id);

}

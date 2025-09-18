package ru.romanov.marketplace.productservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.entity.user.Courier;
import ru.romanov.marketplace.productservice.service.DeliveryService;
import ru.romanov.marketplace.productservice.entity.delivery.Delivery;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.repository.CourierRepository;
import ru.romanov.marketplace.productservice.repository.DeliveryRepository;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryStatus;
import ru.romanov.marketplace.productservice.entity.delivery.CourierDelivery;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryAddress;
import ru.romanov.marketplace.productservice.entity.delivery.PickupPointDelivery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    static String DELIVERY_NOT_FOUND_TEMPLATE = "Delivery with id %s not found";

    DeliveryRepository deliveryRepository;
    CourierRepository courierRepository;

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public List<Delivery> findByStatus(DeliveryStatus status) {
        return deliveryRepository.findAllByStatus(status);
    }

    @Override
    public List<CourierDelivery> findCourierDeliveriesByCourierId(Long courierId) {
        return deliveryRepository.findByCourierId(courierId);
    }

    @Override
    public List<PickupPointDelivery> findPickupDeliveriesByAddress(PointAddress address) {
        return deliveryRepository.findAllPickupPointDeliveriesByAddress(address.toString());
    }

    @Override
    public List<CourierDelivery> findCourierDeliveriesByAddress(DeliveryAddress address) {
        return deliveryRepository.findAllCourierDeliveriesByAddress(address.toString());
    }

    @Override
    public List<Delivery> findByDeliveryDate(LocalDate date) {
        return deliveryRepository.findAllByDeliveryDate(date);
    }

    @Override
    public Delivery updateStatus(Long deliveryId, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(DELIVERY_NOT_FOUND_TEMPLATE, deliveryId)));
        delivery.setStatus(status);
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDeliveryTime(Long deliveryId, LocalDateTime deliveryTime) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(DELIVERY_NOT_FOUND_TEMPLATE, deliveryId)));
        delivery.setDeliveryTime(deliveryTime);
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery updateDeliveryCourier(Long deliveryId, Long courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Courier with id %s not found", courierId)));

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(DELIVERY_NOT_FOUND_TEMPLATE, deliveryId)));

        if (!(delivery instanceof CourierDelivery courierDelivery)) {
            throw new IllegalArgumentException(
                    String.format("Delivery with id %s is not a courier delivery", deliveryId));
        }

        courierDelivery.setCourier(courier);
        return deliveryRepository.save(courierDelivery);
    }

    @Override
    public void deleteById(Long id) {
        if (!deliveryRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(DELIVERY_NOT_FOUND_TEMPLATE, id));
        }
        deliveryRepository.deleteById(id);
    }
}

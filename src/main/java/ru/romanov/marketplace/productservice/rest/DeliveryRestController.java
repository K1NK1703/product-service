package ru.romanov.marketplace.productservice.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.marketplace.productservice.service.DeliveryService;
import ru.romanov.marketplace.productservice.entity.delivery.Delivery;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryStatus;
import ru.romanov.marketplace.productservice.entity.delivery.CourierDelivery;
import ru.romanov.marketplace.productservice.entity.delivery.DeliveryAddress;
import ru.romanov.marketplace.productservice.entity.delivery.PickupPointDelivery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeliveryRestController {

    DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable Long id) {
        return deliveryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Delivery>> getDeliveriesByStatus(@PathVariable DeliveryStatus status) {
        return ResponseEntity.ok(deliveryService.findByStatus(status));
    }

    @GetMapping("/courier/{courierId}")
    public ResponseEntity<List<CourierDelivery>> getCourierDeliveries(@PathVariable Long courierId) {
        return ResponseEntity.ok(deliveryService.findCourierDeliveriesByCourierId(courierId));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Delivery>> getDeliveriesByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(deliveryService.findByDeliveryDate(date));
    }

    @GetMapping("/stats/today")
    public ResponseEntity<List<Delivery>> getTodayDeliveries() {
        List<Delivery> deliveries = deliveryService.findByDeliveryDate(LocalDate.now());
        return ResponseEntity.ok(deliveries);
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        Delivery savedDelivery = deliveryService.save(delivery);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDelivery);
    }

    @PostMapping("/pickup/search")
    public ResponseEntity<List<PickupPointDelivery>> getPickupDeliveriesByAddress(@RequestBody PointAddress address) {
        return ResponseEntity.ok(deliveryService.findPickupDeliveriesByAddress(address));
    }

    @PostMapping("/courier/address/search")
    public ResponseEntity<List<CourierDelivery>> getCourierDeliveriesByAddress(@RequestBody DeliveryAddress address) {
        return ResponseEntity.ok(deliveryService.findCourierDeliveriesByAddress(address));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(
            @PathVariable Long id,
            @RequestParam DeliveryStatus status) {
        try {
            Delivery updatedDelivery = deliveryService.updateStatus(id, status);
            return ResponseEntity.ok(updatedDelivery);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/delivery-time")
    public ResponseEntity<Delivery> updateDeliveryTime(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime deliveryTime) {
        try {
            Delivery updatedDelivery = deliveryService.updateDeliveryTime(id, deliveryTime);
            return ResponseEntity.ok(updatedDelivery);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/courier")
    public ResponseEntity<Delivery> updateDeliveryCourier(
            @PathVariable Long id,
            @RequestParam Long courierId) {
        try {
            Delivery updatedDelivery = deliveryService.updateDeliveryCourier(id, courierId);
            return ResponseEntity.ok(updatedDelivery);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        try {
            deliveryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

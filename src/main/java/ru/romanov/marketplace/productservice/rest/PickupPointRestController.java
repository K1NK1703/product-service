package ru.romanov.marketplace.productservice.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPoint;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.service.PickupPointService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pickup-points")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PickupPointRestController {

    PickupPointService pickupPointService;

    @GetMapping
    public ResponseEntity<List<PickupPoint>> getAllPickupPoints() {
        return ResponseEntity.ok(pickupPointService.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<PickupPoint> getPickupPointByAddress(@RequestBody PointAddress address) {
        return pickupPointService.findByAddress(address)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PickupPoint> createPickupPoint(@RequestBody PickupPoint pickupPoint) {
        PickupPoint savedPoint = pickupPointService.save(pickupPoint);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPoint);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePickupPoint(@RequestBody PointAddress address) {
        try {
            pickupPointService.deleteByAddress(address);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

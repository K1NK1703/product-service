package ru.romanov.marketplace.productservice.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.marketplace.productservice.entity.user.Courier;
import ru.romanov.marketplace.productservice.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/couriers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourierRestController {

    CourierService courierService;

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers() {
        return ResponseEntity.ok(courierService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long id) {
        return courierService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username")
    public ResponseEntity<Courier> getCourierByUsername(@RequestParam String username) {
        return courierService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<Courier> getCourierByEmail(@RequestParam String email) {
        return courierService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier savedCourier = courierService.save(courier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Courier> updateCourier(
            @PathVariable Long id,
            @RequestBody Courier updatedCourier) {
        try {
            Courier result = courierService.updateCourier(id, updatedCourier);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        courierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

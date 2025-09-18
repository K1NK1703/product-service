package ru.romanov.marketplace.productservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.romanov.marketplace.productservice.entity.user.Courier;
import ru.romanov.marketplace.productservice.service.CourierService;
import ru.romanov.marketplace.productservice.repository.CourierRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourierServiceImpl implements CourierService {

    CourierRepository courierRepository;

    @Override
    public Courier save(Courier courier) {
        return courierRepository.save(courier);
    }

    @Override
    public List<Courier> findAll() {
        return courierRepository.findAll();
    }

    @Override
    public Optional<Courier> findById(Long id) {
        return courierRepository.findById(id);
    }

    @Override
    public Optional<Courier> findByUsername(String username) {
        return courierRepository.findByUsername(username);
    }

    @Override
    public Optional<Courier> findByEmail(String email) {
        return courierRepository.findByEmail(email);
    }

    @Override
    public Courier updateCourier(Long courierId, Courier updated) {
        Courier courier = findById(courierId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Courier with id %s not found", courierId)));
        courier.setUsername(updated.getUsername());
        courier.setEmail(updated.getEmail());
        courier.setPhone(updated.getPhone());
        courier.setPassword(updated.getPassword());
        courier.setFirstName(updated.getFirstName());
        courier.setLastName(updated.getLastName());
        courier.setVehicleType(updated.getVehicleType());

        return courierRepository.save(courier);
    }

    @Override
    public void deleteById(Long id) {
        if (!courierRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Courier with id %s not found", id));
        }
        courierRepository.deleteById(id);
    }
}

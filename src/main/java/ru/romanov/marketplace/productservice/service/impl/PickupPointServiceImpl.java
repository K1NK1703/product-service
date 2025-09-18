package ru.romanov.marketplace.productservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.romanov.marketplace.productservice.mapper.PickupPointMapper;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPoint;
import ru.romanov.marketplace.productservice.service.PickupPointService;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.repository.PickupPointDslRepository;
import ru.romanov.marketplace.productservice.jooq.tables.records.PickupPointsRecord;

import java.util.List;
import java.util.Optional;

import static ru.romanov.marketplace.productservice.mapper.PickupPointMapper.toRecord;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PickupPointServiceImpl implements PickupPointService {

    PickupPointDslRepository dslRepository;

    @Override
    public PickupPoint save(PickupPoint pickupPoint) {
        Optional<PickupPoint> existing = dslRepository
                .findByAddressWithContact(pickupPoint.getAddress())
                .map(PickupPointMapper::toEntity);

        PickupPointsRecord record = toRecord(pickupPoint);

        if (existing.isPresent()) {
            // UPDATE
            dslRepository.update(record);
        } else {
            // INSERT
            dslRepository.insert(record);
        }

        return dslRepository.findByAddressWithContact(pickupPoint.getAddress())
                .map(PickupPointMapper::toEntity)
                .orElseThrow();
    }

    @Override
    public List<PickupPoint> findAll() {
        return dslRepository.findAll()
                .stream()
                .map(PickupPointMapper::toEntity)
                .toList();
    }

    @Override
    public Optional<PickupPoint> findByAddress(PointAddress address) {
        return dslRepository.findByAddressWithContact(address)
                .map(PickupPointMapper::toEntity);
    }

    @Override
    public void deleteByAddress(PointAddress address) {
        if (!dslRepository.existsByAddress(address)) {
            throw new EntityNotFoundException(String.format("PickupPoint with address %s not found", address));
        }
        dslRepository.deleteByAddress(address);
    }
}

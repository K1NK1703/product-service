package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContactsPojo;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public interface PointContactRepository {

    void create(PointContactsPojo contactsInfoPojo, Set<PickupPointsPojo> pickupPoints);

    void update(PointContactsPojo contactsInfoPojo, Set<PickupPointsPojo> pickupPoints);

    Optional<PointContactsPojo> findById(UUID id, Boolean forUpdate);

    boolean existsByIdWithLock(UUID id);

}

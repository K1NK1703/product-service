package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContacts;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public interface PointContactInfoRepository {

    void create(PointContacts contactsInfoPojo, Set<PickupPoints> pickupPoints);

    void update(PointContacts contactsInfoPojo, Set<PickupPoints> pickupPoints);

    Optional<PointContacts> findById(UUID id, Boolean forUpdate);

    boolean existsByIdWithLock(UUID id);

}

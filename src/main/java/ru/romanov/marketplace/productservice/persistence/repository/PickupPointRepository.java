package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;
import ru.romanov.marketplace.productservice.persistence.view.PickupPointView;
import ru.romanov.marketplace.productservice.persistence.filter.PickupPointFilter;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public interface PickupPointRepository {

    void create(PickupPoints pickupPointPojo, Set<Employees> employees);

    void update(PickupPoints pickupPointPojo, Set<Employees> employees);

    Optional<PickupPoints> findById(UUID id, Boolean forUpdate);

    PickupPoints findOne(PickupPointFilter filter, Boolean forUpdate);

    PickupPointView findOneView(PickupPointFilter filter, Boolean forUpdate);

    boolean existsByIdWithLock(UUID id);

}

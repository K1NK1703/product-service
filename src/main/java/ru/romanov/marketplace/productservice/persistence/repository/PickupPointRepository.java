package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;
import ru.romanov.marketplace.productservice.persistence.view.PickupPointView;
import ru.romanov.marketplace.productservice.persistence.filter.PickupPointFilter;

import java.util.Set;
import java.util.UUID;

public interface PickupPointRepository {

    void create(PickupPointsPojo pickupPointPojo, Set<EmployeesPojo> employees);

    void update(PickupPointsPojo pickupPointPojo, Set<EmployeesPojo> employees);

    PickupPointsPojo findOne(PickupPointFilter filter, Boolean forUpdate);

    PickupPointView findOneView(PickupPointFilter filter, Boolean forUpdate);

    boolean existsByIdWithLock(UUID id);

}

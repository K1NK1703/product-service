package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository {

    void create(EmployeesPojo pojo);

    void update(EmployeesPojo pojo);

    List<EmployeesPojo> findByFilter(EmployeeFilter filter, Boolean forUpdate);

    void delete(UUID id);

    boolean existsByIdWithLock(UUID id);

}

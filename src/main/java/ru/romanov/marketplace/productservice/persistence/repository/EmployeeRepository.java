package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface EmployeeRepository {

    UUID create(Employees employeePojo);

    UUID update(Employees employeePojo);

    Optional<Employees> findById(UUID id, Boolean forUpdate);

    List<Employees> findByFilter(EmployeeFilter filter, Boolean forUpdate);

    boolean existsByIdWithLock(UUID id);

}

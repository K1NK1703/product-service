package ru.romanov.marketplace.productservice.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.jooq.tables.records.EmployeesRecord;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;
import ru.romanov.marketplace.productservice.persistence.PersistenceExceptionMapper;
import ru.romanov.marketplace.productservice.persistence.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static ru.romanov.marketplace.productservice.persistence.repository.Tables.EMPLOYEES_TABLE;

@Repository
@RequiredArgsConstructor
public class JooqEmployeeRepository implements EmployeeRepository {

    private final DSLContext dslContext;
    private final PersistenceExceptionMapper persistenceExceptionMapper;

    @Override
    public UUID create(Employees employeePojo) {
        try {
            EmployeesRecord employeesRecord = dslContext.newRecord(EMPLOYEES_TABLE, employeePojo);
            employeesRecord.store();
            return employeesRecord.getId();
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public UUID update(Employees employeePojo) {
        try {
            EmployeesRecord employeesRecord = dslContext.newRecord(EMPLOYEES_TABLE, employeePojo);
            employeesRecord.update();
            return employeesRecord.getId();
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public Optional<Employees> findById(UUID id, Boolean forUpdate) {
        final Condition condition = composeDefaultFindOneCondition(id);

        final var selectConditionStep = dslContext.selectFrom(EMPLOYEES_TABLE)
                .where(condition);

        final Optional<EmployeesRecord> record = forUpdate ?
                selectConditionStep.forUpdate().of(EMPLOYEES_TABLE).fetchOptional() :
                selectConditionStep.fetchOptional();

        return record.map(r -> r.into(Employees.class));
    }

    @Override
    public List<Employees> findByFilter(EmployeeFilter filter, Boolean forUpdate) {
        final Condition condition = composeEmployeeFilter(filter);

        final var selectConditionStep = dslContext.selectFrom(EMPLOYEES_TABLE)
                .where(condition);

        final List<EmployeesRecord> records = forUpdate ?
                selectConditionStep.forUpdate().of(EMPLOYEES_TABLE).fetch() :
                selectConditionStep.fetch();

        return records.stream()
                .map(r -> r.into(Employees.class))
                .toList();
    }

    @Override
    public boolean existsByIdWithLock(UUID id) {
        final Condition condition = composeDefaultFindOneCondition(id);

        return dslContext.selectOne()
                .from(EMPLOYEES_TABLE)
                .where(condition)
                .forUpdate()
                .fetchOptional()
                .isPresent();
    }

    private Condition composeDefaultFindOneCondition(UUID id) {
        return EMPLOYEES_TABLE.ID.eq(id);
    }

    private Condition composeEmployeeFilter(EmployeeFilter filter) {
        final AtomicReference<Condition> condition = new AtomicReference<>(DSL.noCondition());

        Optional.ofNullable(filter.getRole())
                .ifPresent(role -> condition.set(condition.get().and(EMPLOYEES_TABLE.ROLE.eq(role.toUpperCase()))));

        Optional.ofNullable(filter.getUsername())
                .ifPresent(username -> condition.set(condition.get().and(EMPLOYEES_TABLE.USERNAME.eq(username))));

        Optional.ofNullable(filter.getPickupPointId())
                .ifPresent(pickupPointId ->
                        condition.set(condition.get().and(EMPLOYEES_TABLE.PICKUP_POINT_ID.eq(pickupPointId))));

        return condition.get();
    }
}

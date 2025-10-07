package ru.romanov.marketplace.productservice.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
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
    public void create(EmployeesPojo pojo) {
        try {
            EmployeesRecord employeesRecord = dslContext.newRecord(EMPLOYEES_TABLE, pojo);
            employeesRecord.store();
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public void update(EmployeesPojo pojo) {
        try {
            EmployeesRecord employeesRecord = dslContext.newRecord(EMPLOYEES_TABLE, pojo);
            dslContext.executeUpdate(employeesRecord);
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public List<EmployeesPojo> findByFilter(EmployeeFilter filter, Boolean forUpdate) {
        final Condition condition = composeEmployeeFilter(filter);

        final var selectConditionStep = dslContext.selectFrom(EMPLOYEES_TABLE)
                .where(condition);

        final List<EmployeesRecord> records = forUpdate ?
                selectConditionStep.forUpdate().of(EMPLOYEES_TABLE).fetch() :
                selectConditionStep.fetch();

        return records.stream()
                .map(r -> r.into(EmployeesPojo.class))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        try {
            if (existsByIdWithLock(id)) {
                dslContext.deleteFrom(EMPLOYEES_TABLE)
                        .where(EMPLOYEES_TABLE.ID.eq(id))
                        .execute();
            }
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
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

        Optional.ofNullable(filter.id())
                .ifPresent(id -> condition.set(condition.get().and(EMPLOYEES_TABLE.ID.eq(id))));

        Optional.ofNullable(filter.role())
                .ifPresent(role -> condition.set(condition.get().and(EMPLOYEES_TABLE.ROLE.eq(role.toUpperCase()))));

        Optional.ofNullable(filter.username())
                .ifPresent(username -> condition.set(condition.get().and(EMPLOYEES_TABLE.USERNAME.eq(username))));

        Optional.ofNullable(filter.pickupPointId())
                .ifPresent(pickupPointId ->
                        condition.set(condition.get().and(EMPLOYEES_TABLE.PICKUP_POINT_ID.eq(pickupPointId))));

        return condition.get();
    }
}

package ru.romanov.marketplace.productservice.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Repository;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;
import ru.romanov.marketplace.productservice.jooq.tables.records.PickupPointsRecord;
import ru.romanov.marketplace.productservice.persistence.view.PickupPointView;
import ru.romanov.marketplace.productservice.persistence.PersistenceException;
import ru.romanov.marketplace.productservice.persistence.filter.PickupPointFilter;
import ru.romanov.marketplace.productservice.persistence.PersistenceExceptionMapper;
import ru.romanov.marketplace.productservice.persistence.mapper.PickupPointViewMapper;
import ru.romanov.marketplace.productservice.persistence.repository.PickupPointRepository;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static ru.romanov.marketplace.productservice.persistence.repository.Tables.EMPLOYEES_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.PICKUP_POINTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Multisets.PICKUP_POINT_EMPLOYEES_MULTISET;

@Repository
@RequiredArgsConstructor
public class JooqPickupPointRepository implements PickupPointRepository {

    private final DSLContext dslContext;
    private final PickupPointViewMapper pickupPointViewMapper;
    private final PersistenceExceptionMapper persistenceExceptionMapper;

    @Override
    public void create(PickupPointsPojo pickupPointPojo, Set<EmployeesPojo> employees) {
        try {
            PickupPointsRecord pickupPointsRecord = dslContext.newRecord(PICKUP_POINTS_TABLE, pickupPointPojo);
            pickupPointsRecord.store();

            if (!CollectionUtils.isEmpty(employees)) {
                insertPickupPointsEmployees(employees);
            }
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public void update(PickupPointsPojo pickupPointPojo, Set<EmployeesPojo> employees) {
        try {
            PickupPointsRecord pickupPointsRecord = dslContext.newRecord(PICKUP_POINTS_TABLE, pickupPointPojo);
            dslContext.executeUpdate(pickupPointsRecord);

            UUID pickupPointId = pickupPointPojo.getId();

            if (!CollectionUtils.isEmpty(employees)) {
                updatePickupPointEmployees(employees, pickupPointId);
            }
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public PickupPointsPojo findOne(PickupPointFilter filter, Boolean forUpdate) {
        final Condition condition = composePickupPointFilter(filter);

        final var selectConditionStep = dslContext.selectFrom(PICKUP_POINTS_TABLE)
                .where(condition);

        final Optional<PickupPointsRecord> record = forUpdate ?
                selectConditionStep.forUpdate().of(PICKUP_POINTS_TABLE).fetchOptional() :
                selectConditionStep.fetchOptional();

        return record.map(r -> r.into(PickupPointsPojo.class))
                .orElseThrow(() -> new PersistenceException.NotFound("PickupPoint(filter=%s) not found".formatted(filter)));
    }

    @Override
    public PickupPointView findOneView(PickupPointFilter filter, Boolean forUpdate) {
        final Condition condition = composePickupPointFilter(filter);
        final var selectConditionStep = selectPickupPointView(condition);
        final PickupPointView pickupPointView =
                (forUpdate ? selectConditionStep.forUpdate().of(PICKUP_POINTS_TABLE) : selectConditionStep)
                        .fetchOne(pickupPointViewMapper);

        return Optional.ofNullable(pickupPointView)
                .orElseThrow(() -> new PersistenceException.NotFound("PickupPoint(filter=%s) not found".formatted(filter)));
    }

    @Override
    public boolean existsByIdWithLock(UUID id) {
        final Condition condition = composeDefaultFindOneCondition(id);

        return dslContext.selectOne()
                .from(PICKUP_POINTS_TABLE)
                .where(condition)
                .forUpdate()
                .fetchOptional()
                .isPresent();
    }

    private void insertPickupPointsEmployees(Set<EmployeesPojo> employees) {
        if (!CollectionUtils.isEmpty(employees)) {
            dslContext.batchInsert(
                    employees.stream()
                            .map(pickupPointEmployee -> dslContext.newRecord(EMPLOYEES_TABLE, pickupPointEmployee))
                            .toList()
            ).execute();
        }
    }

    private void updatePickupPointEmployees(Set<EmployeesPojo> employees, UUID pickupPointId) {
        dslContext.deleteFrom(EMPLOYEES_TABLE)
                .where(EMPLOYEES_TABLE.PICKUP_POINT_ID.eq(pickupPointId))
                .execute();

        insertPickupPointsEmployees(employees);
    }

    private Condition composeDefaultFindOneCondition(UUID id) {
        return PICKUP_POINTS_TABLE.ID.eq(id);
    }

    private Condition composePickupPointFilter(PickupPointFilter filter) {
        final AtomicReference<Condition> condition = new AtomicReference<>(DSL.noCondition());

        Optional.ofNullable(filter.id())
                .ifPresent(id -> condition.set(condition.get().and(PICKUP_POINTS_TABLE.ID.eq(id))));

        Optional.ofNullable(filter.address())
                .ifPresent(address ->
                        condition.set(condition.get()
                                .and(PICKUP_POINTS_TABLE.COUNTRY.eq(address.country()))
                                .and(PICKUP_POINTS_TABLE.REGION.eq(address.region()))
                                .and(PICKUP_POINTS_TABLE.CITY.eq(address.city()))
                                .and(PICKUP_POINTS_TABLE.STREET.eq(address.street()))
                                .and(PICKUP_POINTS_TABLE.HOUSE_NUMBER.eq(address.houseNumber()))
                                .or(PICKUP_POINTS_TABLE.ENTRANCE.eq(address.entrance()))
                        )
                );

        return condition.get();
    }

    private SelectConditionStep<Record> selectPickupPointView(Condition condition) {
        return dslContext
                .select(
                        PICKUP_POINTS_TABLE.asterisk(),
                        PICKUP_POINT_EMPLOYEES_MULTISET
                )
                .from(PICKUP_POINTS_TABLE)
                .where(condition);
    }
}

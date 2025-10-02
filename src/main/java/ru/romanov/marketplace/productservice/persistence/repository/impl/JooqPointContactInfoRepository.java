package ru.romanov.marketplace.productservice.persistence.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Repository;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContacts;
import ru.romanov.marketplace.productservice.jooq.tables.records.PointContactsRecord;
import ru.romanov.marketplace.productservice.persistence.PersistenceExceptionMapper;
import ru.romanov.marketplace.productservice.persistence.repository.PointContactInfoRepository;

import java.util.Set;
import java.util.UUID;
import java.util.Optional;

import static ru.romanov.marketplace.productservice.persistence.repository.Tables.PICKUP_POINTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.POINT_CONTACTS_TABLE;

@Repository
@RequiredArgsConstructor
public class JooqPointContactInfoRepository implements PointContactInfoRepository {

    private final DSLContext dslContext;
    private final PersistenceExceptionMapper persistenceExceptionMapper;

    @Override
    public void create(PointContacts pointContactsPojo, Set<PickupPoints> pickupPoints) {
        try {
            PointContactsRecord pointContactsRecord = dslContext.newRecord(POINT_CONTACTS_TABLE, pointContactsPojo);
            pointContactsRecord.store();

            if (!CollectionUtils.isEmpty(pickupPoints)) {
                insertPointContactsPickupPoint(pickupPoints);
            }
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public void update(PointContacts pointContactsPojo, Set<PickupPoints> pickupPoints) {
        try {
            PointContactsRecord pointContactsRecord = dslContext.newRecord(POINT_CONTACTS_TABLE, pointContactsPojo);
            dslContext.executeUpdate(pointContactsRecord);
            UUID pointContactId = pointContactsPojo.getId();

            updatePointContactPickupPoints(pickupPoints, pointContactId);
        } catch (Throwable throwable) {
            throw persistenceExceptionMapper.map(throwable);
        }
    }

    @Override
    public Optional<PointContacts> findById(UUID id, Boolean forUpdate) {
        final Condition condition = composeDefaultFindOneCondition(id);

        final var selectConditionStep = dslContext.selectFrom(POINT_CONTACTS_TABLE)
                .where(condition);

        final Optional<PointContactsRecord> record = forUpdate ?
                selectConditionStep.forUpdate().of(POINT_CONTACTS_TABLE).fetchOptional() :
                selectConditionStep.fetchOptional();

        return record.map(r -> r.into(PointContacts.class));
    }

    @Override
    public boolean existsByIdWithLock(UUID id) {
        final Condition condition = composeDefaultFindOneCondition(id);

        return dslContext.selectOne()
                .from(POINT_CONTACTS_TABLE)
                .where(condition)
                .forUpdate()
                .fetchOptional()
                .isPresent();
    }

    private void insertPointContactsPickupPoint(Set<PickupPoints> pickupPoints) {
        if (!CollectionUtils.isEmpty(pickupPoints)) {
            dslContext.batchInsert(
                    pickupPoints.stream()
                            .map(pointContactPickupPoint ->
                                    dslContext.newRecord(PICKUP_POINTS_TABLE, pointContactPickupPoint))
                            .toList()
            ).execute();
        }
    }

    private void updatePointContactPickupPoints(Set<PickupPoints> pickupPoints, UUID contactId) {
        dslContext.deleteFrom(PICKUP_POINTS_TABLE)
                .where(PICKUP_POINTS_TABLE.CONTACT_ID.eq(contactId))
                .execute();

        insertPointContactsPickupPoint(pickupPoints);
    }

    private Condition composeDefaultFindOneCondition(UUID id) {
        return POINT_CONTACTS_TABLE.ID.eq(id);
    }
}

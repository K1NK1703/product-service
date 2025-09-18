package ru.romanov.marketplace.productservice.repository;

import org.jooq.Record;
import org.jooq.DSLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.jooq.tables.records.PickupPointsRecord;

import java.util.List;
import java.util.Optional;

import static ru.romanov.marketplace.productservice.jooq.tables.PickupPoints.PICKUP_POINTS;
import static ru.romanov.marketplace.productservice.jooq.tables.PointContactsInfo.POINT_CONTACTS_INFO;

@Repository
@RequiredArgsConstructor
public class PickupPointDslRepository {

    private final DSLContext dsl;

    // CREATE
    public PickupPointsRecord insert(PickupPointsRecord record) {
        record.attach(dsl.configuration());
        record.insert();
        return record;
    }

    // READ by composite key
    public Optional<Record> findByAddressWithContact(PointAddress address) {
        return dsl.select()
                .from(PICKUP_POINTS)
                .join(POINT_CONTACTS_INFO)
                    .on(PICKUP_POINTS.CONTACT_ID.eq(POINT_CONTACTS_INFO.ID))
                .where(PICKUP_POINTS.COUNTRY.eq(address.getCountry()))
                .and(PICKUP_POINTS.REGION.eq(address.getRegion()))
                .and(PICKUP_POINTS.CITY.eq(address.getCity()))
                .and(PICKUP_POINTS.STREET.eq(address.getStreet()))
                .and(PICKUP_POINTS.HOUSE_NUMBER.eq(address.getHouseNumber()))
                .and(PICKUP_POINTS.ENTRANCE.eq(address.getEntrance()))
                .fetchOptional();
    }

    // READ ALL
    public List<Record> findAll() {
        return dsl.select()
                .from(PICKUP_POINTS)
                .join(POINT_CONTACTS_INFO)
                    .on(PICKUP_POINTS.CONTACT_ID.eq(POINT_CONTACTS_INFO.ID))
                .fetch();
    }

    // UPDATE
    public PickupPointsRecord update(PickupPointsRecord record) {
        record.attach(dsl.configuration());
        if (record.modified()) {
            record.update();
        }
        return record;
    }

    // DELETE by composite key
    public int deleteByAddress(PointAddress address) {
        return dsl.deleteFrom(PICKUP_POINTS)
                .where(PICKUP_POINTS.COUNTRY.eq(address.getCountry()))
                .and(PICKUP_POINTS.REGION.eq(address.getRegion()))
                .and(PICKUP_POINTS.CITY.eq(address.getCity()))
                .and(PICKUP_POINTS.STREET.eq(address.getStreet()))
                .and(PICKUP_POINTS.HOUSE_NUMBER.eq(address.getHouseNumber()))
                .and(PICKUP_POINTS.ENTRANCE.eq(address.getEntrance()))
                .execute();
    }

    // EXISTS
    public boolean existsByAddress(PointAddress address) {
        return dsl.fetchExists(
                dsl.selectFrom(PICKUP_POINTS)
                    .where(PICKUP_POINTS.COUNTRY.eq(address.getCountry()))
                    .and(PICKUP_POINTS.REGION.eq(address.getRegion()))
                    .and(PICKUP_POINTS.CITY.eq(address.getCity()))
                    .and(PICKUP_POINTS.STREET.eq(address.getStreet()))
                    .and(PICKUP_POINTS.HOUSE_NUMBER.eq(address.getHouseNumber()))
                    .and(PICKUP_POINTS.ENTRANCE.eq(address.getEntrance()))
        );
    }
}

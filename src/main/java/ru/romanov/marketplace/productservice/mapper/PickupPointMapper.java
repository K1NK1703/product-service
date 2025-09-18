package ru.romanov.marketplace.productservice.mapper;

import org.jooq.Record;
import ru.romanov.marketplace.productservice.entity.pickup.PickupPoint;
import ru.romanov.marketplace.productservice.entity.pickup.PointAddress;
import ru.romanov.marketplace.productservice.entity.pickup.PointContactInfo;
import ru.romanov.marketplace.productservice.entity.pickup.WorkingHours;
import ru.romanov.marketplace.productservice.jooq.tables.records.PickupPointsRecord;

import java.math.BigDecimal;

public final class PickupPointMapper {

    public static PickupPointsRecord toRecord(PickupPoint entity) {
        PickupPointsRecord record = new PickupPointsRecord();
        if (entity.getAddress() != null) {
            PointAddress address = entity.getAddress();
            record.setCountry(address.getCountry());
            record.setRegion(address.getRegion());
            record.setCity(address.getCity());
            record.setStreet(address.getStreet());
            record.setHouseNumber(address.getHouseNumber());
            record.setEntrance(address.getEntrance());
        }
        record.setRating(entity.getRating());
        if (entity.getWorkingHours() != null) {
            WorkingHours workingHours = entity.getWorkingHours();
            record.setOpenTime(workingHours.getOpenTime());
            record.setCloseTime(workingHours.getCloseTime());
        }
        record.setContactId(entity.getContact() != null ? entity.getContact().getId() : null);
        return record;
    }

    public static PickupPoint toEntity(Record record) {
        if (record == null) return null;

        PointAddress address = PointAddress.builder()
                .country(record.getValue("country", String.class))
                .region(record.getValue("region", String.class))
                .city(record.getValue("city", String.class))
                .street(record.getValue("street", String.class))
                .houseNumber(record.getValue("house_number", String.class))
                .entrance(record.getValue("entrance", String.class))
                .build();

        WorkingHours workingHours = WorkingHours.builder()
                .openTime(record.getValue("open_time", String.class))
                .closeTime(record.getValue("close_time", String.class))
                .build();

        PointContactInfo contact = PointContactInfo.builder()
                .id(record.getValue("id", Long.class))
                .phoneNumber(record.getValue("phone_number", String.class))
                .site(record.getValue("site", String.class))
                .build();

        return PickupPoint.builder()
                .address(address)
                .rating(record.getValue("rating", BigDecimal.class))
                .workingHours(workingHours)
                .contact(contact)
                .build();
    }
}

package ru.romanov.marketplace.productservice.persistence.mapper;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;
import ru.romanov.marketplace.productservice.persistence.view.PickupPointView;

import java.util.HashSet;

import static ru.romanov.marketplace.productservice.persistence.repository.Tables.PICKUP_POINTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Multisets.PICKUP_POINT_EMPLOYEES_MULTISET;

@Component
public class PickupPointViewMapper implements RecordMapper<Record, PickupPointView> {

    @Override
    public PickupPointView map(Record record) {
        return new PickupPointView(
                record.into(PICKUP_POINTS_TABLE).into(PickupPoints.class),
                new HashSet<>(record.get(PICKUP_POINT_EMPLOYEES_MULTISET))
        );
    }
}

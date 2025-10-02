package ru.romanov.marketplace.productservice.persistence.mapper;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContacts;
import ru.romanov.marketplace.productservice.persistence.view.PointContactInfoView;

import java.util.HashSet;

import static ru.romanov.marketplace.productservice.persistence.repository.Tables.POINT_CONTACTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Multisets.PICKUP_POINTS_MULTISET;

@Component
public class PointContactInfoViewMapper implements RecordMapper<Record, PointContactInfoView> {

    @Override
    public PointContactInfoView map(Record record) {
        return new PointContactInfoView(
                record.into(POINT_CONTACTS_TABLE).into(PointContacts.class),
                new HashSet<>(record.get(PICKUP_POINTS_MULTISET))
        );
    }
}

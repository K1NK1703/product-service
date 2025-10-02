package ru.romanov.marketplace.productservice.persistence.repository;

import org.jooq.Field;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;

import java.util.List;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.selectFrom;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.EMPLOYEES_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.PICKUP_POINTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.POINT_CONTACTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.PICKUP_POINTS_MULTISET_ALIAS;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.PICKUP_POINT_EMPLOYEES_MULTISET_ALIAS;

public class Multisets {

    public static final Field<List<PickupPoints>> PICKUP_POINTS_MULTISET =
            multiset(
                    selectFrom(PICKUP_POINTS_TABLE)
                            .where(PICKUP_POINTS_TABLE.CONTACT_ID.eq(POINT_CONTACTS_TABLE.ID)))
                    .as(PICKUP_POINTS_MULTISET_ALIAS)
                    .convertFrom(record ->
                            record.into(PickupPoints.class)
                    );

    public static final Field<List<Employees>> PICKUP_POINT_EMPLOYEES_MULTISET =
            multiset(
                    selectFrom(EMPLOYEES_TABLE)
                            .where(EMPLOYEES_TABLE.PICKUP_POINT_ID.eq(PICKUP_POINTS_TABLE.ID)))
                    .as(PICKUP_POINT_EMPLOYEES_MULTISET_ALIAS)
                    .convertFrom(record ->
                            record.into(Employees.class)
                    );
}

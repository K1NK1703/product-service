package ru.romanov.marketplace.productservice.persistence.repository;

import org.jooq.Field;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;

import java.util.List;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.selectFrom;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.EMPLOYEES_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.PICKUP_POINTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Tables.POINT_CONTACTS_TABLE;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.PICKUP_POINTS_MULTISET_ALIAS;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.PICKUP_POINT_EMPLOYEES_MULTISET_ALIAS;

public class Multisets {

    public static final Field<List<PickupPointsPojo>> PICKUP_POINTS_MULTISET =
            multiset(
                    selectFrom(PICKUP_POINTS_TABLE)
                            .where(PICKUP_POINTS_TABLE.CONTACT_ID.eq(POINT_CONTACTS_TABLE.ID)))
                    .as(PICKUP_POINTS_MULTISET_ALIAS)
                    .convertFrom(record ->
                            record.into(PickupPointsPojo.class)
                    );

    public static final Field<List<EmployeesPojo>> PICKUP_POINT_EMPLOYEES_MULTISET =
            multiset(
                    selectFrom(EMPLOYEES_TABLE)
                            .where(EMPLOYEES_TABLE.PICKUP_POINT_ID.eq(PICKUP_POINTS_TABLE.ID)))
                    .as(PICKUP_POINT_EMPLOYEES_MULTISET_ALIAS)
                    .convertFrom(record ->
                            record.into(EmployeesPojo.class)
                    );
}

package ru.romanov.marketplace.productservice.persistence.repository;

import ru.romanov.marketplace.productservice.jooq.tables.Employees;
import ru.romanov.marketplace.productservice.jooq.tables.PickupPoints;
import ru.romanov.marketplace.productservice.jooq.tables.PointContacts;

import static ru.romanov.marketplace.productservice.jooq.Tables.EMPLOYEES;
import static ru.romanov.marketplace.productservice.jooq.Tables.PICKUP_POINTS;
import static ru.romanov.marketplace.productservice.jooq.Tables.POINT_CONTACTS;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.EMPLOYEES_ALIAS;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.PICKUP_POINTS_ALIAS;
import static ru.romanov.marketplace.productservice.persistence.repository.Alias.POINT_CONTACT_INFO_ALIAS;

public class Tables {

    public static final Employees EMPLOYEES_TABLE = EMPLOYEES.as(EMPLOYEES_ALIAS);

    public static final PickupPoints PICKUP_POINTS_TABLE = PICKUP_POINTS.as(PICKUP_POINTS_ALIAS);

    public static final PointContacts POINT_CONTACTS_TABLE = POINT_CONTACTS.as(POINT_CONTACT_INFO_ALIAS);

}

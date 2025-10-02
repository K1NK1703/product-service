package ru.romanov.marketplace.productservice.persistence.view;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContacts;

import java.util.Set;

public record PointContactInfoView(

        PointContacts pointContactsInfo,

        Set<PickupPoints> pickupPoints

) {}

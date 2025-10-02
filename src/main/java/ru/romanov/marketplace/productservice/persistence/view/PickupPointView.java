package ru.romanov.marketplace.productservice.persistence.view;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPoints;

import java.util.Set;

public record PickupPointView(

        PickupPoints pickupPoint,

        Set<Employees> employees

) {}

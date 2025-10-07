package ru.romanov.marketplace.productservice.persistence.view;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;

import java.util.Set;

public record PickupPointView(

        PickupPointsPojo pickupPoint,

        Set<EmployeesPojo> employees

) {}

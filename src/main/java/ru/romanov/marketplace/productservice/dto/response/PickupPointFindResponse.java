package ru.romanov.marketplace.productservice.dto.response;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;

import java.util.Set;

public record PickupPointFindResponse(

        String address,

        String workingHours,

        String contactId,

        String rating,

        Set<EmployeesPojo> employees

) {}

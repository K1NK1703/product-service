package ru.romanov.marketplace.productservice.persistence.filter;

import java.util.UUID;

public record EmployeeFilter(

        UUID id,

        String role,

        String username,

        UUID pickupPointId

) {}

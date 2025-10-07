package ru.romanov.marketplace.productservice.dto.response;

public record EmployeeFindResponse(

        String firstName,

        String lastName,

        String phoneNumber,

        String username,

        String email,

        String role,

        String pickupPointId

) {}

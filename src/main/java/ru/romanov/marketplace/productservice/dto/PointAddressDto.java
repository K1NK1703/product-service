package ru.romanov.marketplace.productservice.dto;

public record PointAddressDto(

        String country,

        String region,

        String city,

        String street,

        String houseNumber,

        String entrance

) {}

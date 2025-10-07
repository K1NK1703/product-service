package ru.romanov.marketplace.productservice.persistence.filter;

import ru.romanov.marketplace.productservice.dto.PointAddressDto;

import java.util.UUID;

public record PickupPointFilter(

        UUID id,

        PointAddressDto address

) {}

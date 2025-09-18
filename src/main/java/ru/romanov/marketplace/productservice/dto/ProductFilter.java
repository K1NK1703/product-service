package ru.romanov.marketplace.productservice.dto;

import java.math.BigDecimal;

public record ProductFilter(
        String nameContains,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Double minDiscount,
        Double maxDiscount
) {}

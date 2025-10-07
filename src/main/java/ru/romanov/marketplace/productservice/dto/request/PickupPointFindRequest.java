package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PickupPointFindRequest(

        @JsonProperty(value = "id") String id,

        @JsonProperty(value = "country", required = true) String country,

        @JsonProperty(value = "region", required = true) String region,

        @JsonProperty(value = "city", required = true) String city,

        @JsonProperty(value = "street", required = true) String street,

        @JsonProperty(value = "houseNumber", required = true) String houseNumber,

        @JsonProperty(value = "entrance") String entrance

) {}

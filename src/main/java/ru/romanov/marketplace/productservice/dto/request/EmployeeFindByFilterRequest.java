package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeFindByFilterRequest(

        @JsonProperty(value = "role") String role,

        @JsonProperty(value = "username") String username,

        @JsonProperty(value = "pickupPointId") String pickupPointId

) {}

package ru.romanov.marketplace.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeFindResponse(

        @JsonProperty(value = "firstName", required = true) String firstName,

        @JsonProperty(value = "lastName") String lastName,

        @JsonProperty(value = "phoneNumber", required = true) String phoneNumber,

        @JsonProperty(value = "username", required = true) String username,

        @JsonProperty(value = "email", required = true) String email,

        @JsonProperty(value = "role", required = true) String role,

        @JsonProperty(value = "pickupPointId") String pickupPointId

) {}

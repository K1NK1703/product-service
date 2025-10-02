package ru.romanov.marketplace.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeResponse(

        @JsonProperty(value = "id", required = true) String id,

        @JsonProperty(value = "username", required = true) String username,

        @JsonProperty(value = "email", required = true) String email,

        @JsonProperty(value = "password", required = true) String password,

        @JsonProperty(value = "pickupPointId") String pickupPointId,

        @JsonProperty("message") String message

) {}

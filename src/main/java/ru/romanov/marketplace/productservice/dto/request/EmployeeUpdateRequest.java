package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeUpdateRequest(

        @JsonProperty(value = "id", required = true) String id,

        @JsonProperty(value = "firstName") String firstName,

        @JsonProperty(value = "lastName") String lastName,

        @JsonProperty(value = "phoneNumber") String phoneNumber,

        @JsonProperty(value = "username") String username,

        @JsonProperty(value = "password") String password,

        @JsonProperty(value = "email") String email,

        @JsonProperty(value = "role") String role,

        @JsonProperty(value = "pickupPointId") String pickupPointId,

        @JsonProperty(value = "salary") String salary

) {}

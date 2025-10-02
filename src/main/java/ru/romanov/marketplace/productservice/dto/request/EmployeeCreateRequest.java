package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeCreateRequest(

        @JsonProperty(value = "firstName", required = true) String firstName,

        @JsonProperty(value = "lastName") String lastName,

        @JsonProperty(value = "phoneNumber", required = true) String phoneNumber,

        @JsonProperty(value = "username", required = true) String username,

        @JsonProperty(value = "password", required = true) String password,

        @JsonProperty(value = "email", required = true) String email,

        @JsonProperty(value = "role", required = true) String role,

        @JsonProperty(value = "pickupPointId") String pickupPointId,

        @JsonProperty(value = "salary") String salary

) {}

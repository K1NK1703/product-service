package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;

import java.util.Set;

public record PickupPointUpdateRequest(

        @JsonProperty(value = "id", required = true) String id,

        @JsonProperty(value = "contactId") String contactId,

        @JsonProperty(value = "openTime") String openTime,

        @JsonProperty(value = "closeTime") String closeTime,

        @JsonProperty(value = "employees") Set<EmployeesPojo> employees

) {}

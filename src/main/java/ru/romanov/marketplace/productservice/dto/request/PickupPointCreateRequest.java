package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;

import java.util.Set;

public record PickupPointCreateRequest(

        @JsonProperty(value = "contactId") String contactId,

        @JsonProperty(value = "country", required = true) String country,

        @JsonProperty(value = "region", required = true) String region,

        @JsonProperty(value = "city", required = true) String city,

        @JsonProperty(value = "street", required = true) String street,

        @JsonProperty(value = "houseNumber", required = true) String houseNumber,

        @JsonProperty(value = "entrance") String entrance,

        @JsonProperty(value = "openTime", required = true) String openTime,

        @JsonProperty(value = "closeTime", required = true) String closeTime,

        @JsonProperty(value = "employees") Set<EmployeesPojo> employees

) {}

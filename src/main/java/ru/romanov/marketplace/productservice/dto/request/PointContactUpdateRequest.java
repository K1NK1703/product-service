package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;

import java.util.Set;

public record PointContactUpdateRequest(

        @JsonProperty(value = "id", required = true) String id,

        @JsonProperty(value = "phoneNumber") String phoneNumber,

        @JsonProperty(value = "site") String site,

        @JsonProperty(value = "pickupPoints") Set<PickupPointsPojo> pickupPoints

) {}

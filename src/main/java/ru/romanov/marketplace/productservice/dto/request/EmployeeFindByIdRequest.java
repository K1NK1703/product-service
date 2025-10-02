package ru.romanov.marketplace.productservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeFindByIdRequest(

        @JsonProperty(value = "id") String id

) {}

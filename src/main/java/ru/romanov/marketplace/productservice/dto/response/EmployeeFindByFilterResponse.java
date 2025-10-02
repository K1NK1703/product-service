package ru.romanov.marketplace.productservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EmployeeFindByFilterResponse(

        @JsonProperty(value = "employees") List<EmployeeFindResponse> employees

) {}

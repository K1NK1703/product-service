package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse create(EmployeeCreateRequest createRequest);

    EmployeeFindResponse findById(String id);

    List<EmployeesPojo> findByFilter(EmployeeFindRequest findByFilterRequest);

    EmployeeResponse update(EmployeeUpdateRequest updateRequest);

    void delete(String id);

}

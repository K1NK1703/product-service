package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByIdRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByFilterRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindByFilterResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeCreateRequest createRequest);

    EmployeeFindResponse findEmployeeById(EmployeeFindByIdRequest findByIdRequest);

    EmployeeFindByFilterResponse findByFilter(EmployeeFindByFilterRequest findByFilterRequest);

    EmployeeResponse updateEmployee(EmployeeUpdateRequest updateRequest);

}

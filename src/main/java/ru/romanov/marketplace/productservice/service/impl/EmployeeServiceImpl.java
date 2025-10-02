package ru.romanov.marketplace.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.aop.Logging;
import ru.romanov.marketplace.productservice.mapper.EmployeeMapper;
import ru.romanov.marketplace.productservice.service.EmployeeService;
import ru.romanov.marketplace.productservice.service.ServiceException;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByIdRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByFilterRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindByFilterResponse;
import ru.romanov.marketplace.productservice.persistence.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String ERROR_MSG = "Message: %s. Error: %s";

    private static final String CREATE_SUCCESS = "Employee %s created Successfully";

    private static final String UPDATE_SUCCESS = "Employee %s updated Successfully";

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Logging(value = "Создание сотрудника ПВЗ", logResult = false)
    @Transactional
    @Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest createRequest) {
        try {
            Employees employee = employeeMapper.employeeCreateToPojo(createRequest);
            UUID id = employeeRepository.create(employee);

            Employees updated = employeeRepository.findById(id, true)
                    .orElseThrow(() -> new ServiceException.NotFoundException("Employee with id %s not found".formatted(id)));

            return employeeMapper.employeeToResponse(updated, CREATE_SUCCESS.formatted(updated.getUsername()));
        } catch (Exception e) {
            throw new ServiceException.CreationException(ERROR_MSG.formatted("Create error", e.getMessage()));
        }
    }

    @Logging(value = "Поиск сотрудника ПВЗ")
    @Transactional
    @Override
    public EmployeeFindResponse findEmployeeById(EmployeeFindByIdRequest findByIdRequest) {
        try {
            String id = Optional.ofNullable(findByIdRequest.id())
                    .orElseThrow(() -> new ServiceException.NotFoundException("Employee id cannot be null"));

            Employees employee = employeeRepository.findById(UUID.fromString(id), true)
                    .orElseThrow(() -> new ServiceException.NotFoundException("Employee with id %s not found".formatted(id)));

            return employeeMapper.employeeToFindResponse(employee);
        } catch (Exception e) {
            throw new ServiceException.NotFoundException(ERROR_MSG.formatted("Found error", e.getMessage()));
        }
    }

    @Logging(value = "Поиск сотрудников ПВЗ по фильтру", logResult = false)
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public EmployeeFindByFilterResponse findByFilter(EmployeeFindByFilterRequest findByFilterRequest) {
        try {
            EmployeeFilter filter = employeeMapper.employeeToFindFilter(findByFilterRequest);
            List<Employees> employeeList = employeeRepository.findByFilter(filter, true);

            return employeeMapper.employeesToFindByFilterResponse(employeeList);
        } catch (Exception e) {
            throw new ServiceException.ConversionException(ERROR_MSG.formatted("Conversion error", e.getMessage()));
        }
    }

    @Logging(value = "Обновление данных о сотруднике ПВЗ", logParams = false, logResult = false)
    @Transactional
    @Override
    public EmployeeResponse updateEmployee(EmployeeUpdateRequest updateRequest) {
        try {
            if (!employeeRepository.existsByIdWithLock(UUID.fromString(updateRequest.id()))) {
                throw new ServiceException.NotFoundException("Employee with id %s not found".formatted(updateRequest.id()));
            }

            Employees employee = employeeMapper.employeeUpdateToPojo(updateRequest);
            UUID id = employeeRepository.update(employee);

            Employees updated = employeeRepository.findById(id, true)
                    .orElseThrow(() -> new ServiceException.NotFoundException("Employee with id %s not found".formatted(id)));

            return employeeMapper.employeeToResponse(updated, UPDATE_SUCCESS.formatted(updated.getUsername()));
        } catch (Exception e) {
            throw new ServiceException.UpdateException(ERROR_MSG.formatted("Update error", e.getMessage()));
        }
    }
}

package ru.romanov.marketplace.productservice.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindByFilterResponse;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.Employees;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByFilterRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.math.BigDecimal;

@Component
public class EmployeeMapper {

    public Employees employeeCreateToPojo(EmployeeCreateRequest request) {
        Employees employee = new Employees();

        employee.setFirstName(request.firstName());

        Optional.ofNullable(request.lastName())
                        .ifPresent(employee::setLastName);

        employee.setPhoneNumber(request.phoneNumber());
        employee.setUsername(request.username());
        employee.setPassword(request.password());
        employee.setEmail(request.email());
        employee.setRole(request.role());

        Optional.of(UUID.fromString(request.pickupPointId()))
                .ifPresent(employee::setPickupPointId);

        Optional.of(BigDecimal.valueOf(Double.parseDouble(request.salary())))
                .ifPresent(employee::setSalary);

        return employee;
    }

    public EmployeeResponse employeeToResponse(Employees employee, String message) {
        String pickupPointId = Optional.ofNullable(employee.getPickupPointId())
                .map(String::valueOf)
                .orElse(null);

        return new EmployeeResponse(
                String.valueOf(employee.getId()),
                employee.getUsername(),
                employee.getEmail(),
                employee.getPassword(),
                pickupPointId,
                message
        );
    }

    public EmployeeFilter employeeToFindFilter(EmployeeFindByFilterRequest request) {
        EmployeeFilter filter = new EmployeeFilter();

        Optional.ofNullable(request.role())
                .filter(StringUtils::isNotEmpty)
                .ifPresent(filter::setRole);

        Optional.ofNullable(request.username())
                .filter(StringUtils::isNotEmpty)
                .ifPresent(filter::setUsername);

        Optional.ofNullable(request.pickupPointId())
                .filter(StringUtils::isNotEmpty)
                .ifPresent(id -> filter.setPickupPointId(UUID.fromString(id)));

        return filter;
    }

    public EmployeeFindResponse employeeToFindResponse(Employees employee) {
        String lastName = Optional.ofNullable(employee.getLastName())
                .map(String::valueOf)
                .orElse(null);

        String pickupPointId = Optional.ofNullable(employee.getPickupPointId())
                .map(String::valueOf)
                .orElse(null);

        return new EmployeeFindResponse(
                employee.getFirstName(),
                lastName,
                employee.getPhoneNumber(),
                employee.getUsername(),
                employee.getEmail(),
                employee.getRole(),
                pickupPointId
        );
    }

    public EmployeeFindByFilterResponse employeesToFindByFilterResponse(List<Employees> employeeList) {
        return new EmployeeFindByFilterResponse(
                employeeList.stream()
                        .map(e -> new EmployeeFindResponse(
                                e.getFirstName(),
                                Optional.ofNullable(e.getLastName())
                                        .map(String::valueOf)
                                        .orElse(null),
                                e.getPhoneNumber(),
                                e.getUsername(),
                                e.getEmail(),
                                e.getRole(),
                                Optional.ofNullable(e.getPickupPointId())
                                        .map(String::valueOf)
                                        .orElse(null)
                        ))
                        .toList()
        );
    }

    public Employees employeeUpdateToPojo(EmployeeUpdateRequest request) {
        Employees employee = new Employees();

        employee.setId(UUID.fromString(request.id()));

        Optional.ofNullable(request.firstName())
                .ifPresent(employee::setFirstName);
        Optional.ofNullable(request.lastName())
                .ifPresent(employee::setLastName);
        Optional.ofNullable(request.phoneNumber())
                .ifPresent(employee::setPhoneNumber);
        Optional.ofNullable(request.username())
                .ifPresent(employee::setUsername);
        Optional.ofNullable(request.password())
                .ifPresent(employee::setPassword);
        Optional.ofNullable(request.email())
                .ifPresent(employee::setEmail);
        Optional.ofNullable(request.role())
                .ifPresent(employee::setRole);
        Optional.of(UUID.fromString(request.pickupPointId()))
                .ifPresent(employee::setPickupPointId);
        Optional.of(BigDecimal.valueOf(Double.parseDouble(request.salary())))
                .ifPresent(employee::setSalary);

        return employee;
    }
}

package ru.romanov.marketplace.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.utils.UUIDGenerator;
import ru.romanov.marketplace.productservice.mapper.EmployeeMapper;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;
import ru.romanov.marketplace.productservice.persistence.repository.EmployeeRepository;
import ru.romanov.marketplace.productservice.service.EmployeeService;
import ru.romanov.marketplace.productservice.exception.ProductException;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

import static ru.romanov.marketplace.productservice.utils.MessageUtils.OPERATION_ERROR;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.EMPLOYEE_NOT_FOUND;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.EMPLOYEE_CREATE_SUCCESS;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.EMPLOYEE_UPDATE_SUCCESS;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UUIDGenerator uuidGenerator;

    private final EmployeeMapper employeeMapper;

    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public EmployeeResponse create(EmployeeCreateRequest createRequest) {
        try {
            UUID id = uuidGenerator.generateUUID();

            EmployeesPojo employee = employeeMapper.toCreatePojo(createRequest);
            employee.setId(id);

            employeeRepository.create(employee);

            EmployeeFindResponse created = findById(String.valueOf(id));

            return new EmployeeResponse(
                    String.valueOf(id),
                    created.username(),
                    created.email(),
                    created.pickupPointId(),
                    EMPLOYEE_CREATE_SUCCESS.formatted(created.username())
            );
        } catch (Exception e) {
            throw new ProductException.CreationException(OPERATION_ERROR.formatted("Create error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public EmployeeFindResponse findById(String id) {
        try {
            EmployeeFilter filter = new EmployeeFilter(
                    UUID.fromString(id),
                    null,
                    null,
                    null
            );

            List<EmployeesPojo> list = employeeRepository.findByFilter(filter, true);

            return Optional.ofNullable(list)
                    .stream()
                    .flatMap(Collection::stream)
                    .findFirst()
                    .map(employeeMapper::toFindResponse)
                    .orElseThrow(() -> new ProductException.NotFoundException(EMPLOYEE_NOT_FOUND.formatted(id)));
        } catch (Exception e) {
            throw new ProductException.NotFoundException(OPERATION_ERROR.formatted("Found error", e.getMessage()));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<EmployeesPojo> findByFilter(EmployeeFindRequest findByFilterRequest) {
        try {
            EmployeeFilter filter = employeeMapper.toFilter(findByFilterRequest);

            return employeeRepository.findByFilter(filter, true);
        } catch (Exception e) {
            throw new ProductException.ConversionException(OPERATION_ERROR.formatted("Found error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public EmployeeResponse update(EmployeeUpdateRequest updateRequest) {
        try {
            UUID id = UUID.fromString(updateRequest.id());

            if (!employeeRepository.existsByIdWithLock(id)) {
                throw new ProductException.NotFoundException(EMPLOYEE_NOT_FOUND.formatted(id));
            }

            EmployeesPojo employee = employeeMapper.toUpdatePojo(updateRequest);
            employeeRepository.update(employee);

            EmployeeFindResponse updated = findById(String.valueOf(id));

            return new EmployeeResponse(
                    updateRequest.id(),
                    updated.username(),
                    updated.email(),
                    updated.pickupPointId(),
                    EMPLOYEE_UPDATE_SUCCESS.formatted(updated.username())
            );
        } catch (Exception e) {
            throw new ProductException.UpdateException(OPERATION_ERROR.formatted("Update error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public void delete(String id) {
        try {
            employeeRepository.delete(UUID.fromString(id));
        } catch (Exception e) {
            throw new ProductException.DeletedException(OPERATION_ERROR.formatted("Delete error", e.getMessage()));
        }
    }
}

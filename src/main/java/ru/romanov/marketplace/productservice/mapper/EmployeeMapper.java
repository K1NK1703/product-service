package ru.romanov.marketplace.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.persistence.filter.EmployeeFilter;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;

import java.util.UUID;
import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    EmployeesPojo toCreatePojo(EmployeeCreateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    EmployeesPojo toUpdatePojo(EmployeeUpdateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "pickupPointId", source = "pickupPointId")
    EmployeeFilter toFilter(EmployeeFindRequest request);

    @Mapping(target = "pickupPointId", source = "pickupPointId")
    EmployeeFindResponse toFindResponse(EmployeesPojo pojo);

    default BigDecimal mapSalary(String salary) {
        return (salary == null || salary.trim().isEmpty()) ? null : new BigDecimal(salary);
    }

    default UUID mapIdToUUID(String id) {
        return (id == null || id.trim().isEmpty()) ? null : UUID.fromString(id);
    }

    default String mapIdToString(UUID id) {
        return (id == null || id.toString().isEmpty()) ? null : id.toString();
    }
}

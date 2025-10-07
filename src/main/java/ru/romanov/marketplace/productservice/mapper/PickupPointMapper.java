package ru.romanov.marketplace.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.CollectionUtils;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;
import ru.romanov.marketplace.productservice.persistence.filter.PickupPointFilter;
import ru.romanov.marketplace.productservice.dto.PointAddressDto;
import ru.romanov.marketplace.productservice.dto.response.PickupPointResponse;
import ru.romanov.marketplace.productservice.dto.request.PickupPointFindRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.PickupPointFindResponse;

import java.util.Set;
import java.util.UUID;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PickupPointMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "contactId", source = "contactId")
    PickupPointsPojo toCreatePojo(PickupPointCreateRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "contactId", source = "contactId")
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "country", ignore = true)
    @Mapping(target = "region", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "street", ignore = true)
    @Mapping(target = "houseNumber", ignore = true)
    @Mapping(target = "entrance", ignore = true)
    PickupPointsPojo toUpdatePojo(PickupPointUpdateRequest request);

    default PickupPointFilter toFilter(PickupPointFindRequest request) {
        PointAddressDto address = new PointAddressDto(
                request.country(),
                request.region(),
                request.city(),
                request.street(),
                request.houseNumber(),
                request.entrance() == null ? null : request.entrance()
        );

        return new PickupPointFilter(
                request.id() == null ? null : UUID.fromString(request.id()),
                address
        );
    }

    default PickupPointResponse toResponse(PickupPointsPojo pojo, String message) {
        String address = Stream.of(
                        pojo.getCountry(),
                        pojo.getRegion(),
                        pojo.getCity(),
                        pojo.getStreet(),
                        pojo.getHouseNumber(),
                        pojo.getEntrance()
                )
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ", "Address={ ", " }"));

        return new PickupPointResponse(
                String.valueOf(pojo.getId()),
                address,
                message
        );
    }

    default PickupPointFindResponse toFindResponse(PickupPointsPojo pojo, Set<EmployeesPojo> set) {
        String address = Stream.of(
                        pojo.getCountry(),
                        pojo.getRegion(),
                        pojo.getCity(),
                        pojo.getStreet(),
                        pojo.getHouseNumber(),
                        pojo.getEntrance()
                )
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ", "Address={ ", " }"));

        String workingHours = Stream.of(
                        pojo.getOpenTime(),
                        pojo.getCloseTime()
                )
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ", "WorkingHours={ ", " }"));

        return new PickupPointFindResponse(
                address ,
                workingHours,
                pojo.getContactId() == null ? null : String.valueOf(pojo.getContactId()),
                pojo.getRating() == null ? null : String.valueOf(pojo.getRating()),
                CollectionUtils.isEmpty(set) ? null : set
        );
    }

    default UUID mapIdToUUUID(String id) {
        return (id == null || id.trim().isEmpty()) ? null : UUID.fromString(id);
    }

    default String mapIdToString(UUID id) {
        return (id == null || id.toString().isEmpty()) ? null : id.toString();
    }
}

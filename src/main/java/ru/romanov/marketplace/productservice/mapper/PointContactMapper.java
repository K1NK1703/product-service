package ru.romanov.marketplace.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContactsPojo;
import ru.romanov.marketplace.productservice.dto.response.PointContactResponse;
import ru.romanov.marketplace.productservice.dto.request.PointContactUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.PointContactCreateRequest;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PointContactMapper {

    @Mapping(target = "id", ignore = true)
    PointContactsPojo toCreatePojo(PointContactCreateRequest request);

    @Mapping(target = "id", source = "id")
    PointContactsPojo toUpdatePojo(PointContactUpdateRequest request);

    @Mapping(target = "message", ignore = true)
    PointContactResponse toResponse(PointContactsPojo pojo);

    default UUID mapIdToUUUID(String id) {
        return (id == null || id.trim().isEmpty()) ? null : UUID.fromString(id);
    }

    default String mapIdToString(UUID id) {
        return (id == null || id.toString().isEmpty()) ? null : id.toString();
    }
}

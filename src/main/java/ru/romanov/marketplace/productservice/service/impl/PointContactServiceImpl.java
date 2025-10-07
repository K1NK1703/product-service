package ru.romanov.marketplace.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.utils.UUIDGenerator;
import ru.romanov.marketplace.productservice.mapper.PointContactMapper;
import ru.romanov.marketplace.productservice.exception.ProductException;
import ru.romanov.marketplace.productservice.service.PointContactService;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PointContactsPojo;
import ru.romanov.marketplace.productservice.persistence.repository.PointContactRepository;
import ru.romanov.marketplace.productservice.dto.response.PointContactResponse;
import ru.romanov.marketplace.productservice.dto.request.PointContactCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.PointContactUpdateRequest;

import java.util.UUID;

import static ru.romanov.marketplace.productservice.utils.MessageUtils.*;

@Service
@RequiredArgsConstructor
public class PointContactServiceImpl implements PointContactService {

    private final UUIDGenerator uuidGenerator;

    private final PointContactMapper pointContactMapper;

    private final PointContactRepository pointContactRepository;

    @Transactional
    @Override
    public PointContactResponse create(PointContactCreateRequest createRequest) {
        try {
            UUID id = uuidGenerator.generateUUID();

            PointContactsPojo pointContact = pointContactMapper.toCreatePojo(createRequest);
            pointContact.setId(id);

            pointContactRepository.create(pointContact, createRequest.pickupPoints());

            PointContactResponse created = findById(String.valueOf(id));

            return new PointContactResponse(
                    created.phoneNumber(),
                    created.site(),
                    POINT_CONTACT_CREATE_SUCCESS.formatted(id)
            );
        } catch (Exception e) {
            throw new ProductException.CreationException(OPERATION_ERROR.formatted("Create error", e.getMessage()));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PointContactResponse findById(String id) {
        try {
            PointContactsPojo pointContact = pointContactRepository.findById(UUID.fromString(id), true)
                    .orElseThrow(() -> new ProductException.NotFoundException(POINT_CONTACT_NOT_FOUND.formatted(id)));

            PointContactResponse response = pointContactMapper.toResponse(pointContact);

            return new PointContactResponse(
                    response.phoneNumber(),
                    response.site(),
                    POINT_CONTACT_SUCCESS_FOUND.formatted(id)
            );
        } catch (Exception e) {
            throw new ProductException.NotFoundException(OPERATION_ERROR.formatted("Found error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public PointContactResponse update(PointContactUpdateRequest updateRequest) {
        try {
            UUID id = UUID.fromString(updateRequest.id());

            if (!pointContactRepository.existsByIdWithLock(id)) {
                throw new ProductException.NotFoundException(POINT_CONTACT_NOT_FOUND.formatted(id));
            }

            PointContactsPojo pointContact = pointContactMapper.toUpdatePojo(updateRequest);
            pointContactRepository.update(pointContact, updateRequest.pickupPoints());

            PointContactResponse updated = findById(String.valueOf(id));

            return new PointContactResponse(
                    updated.phoneNumber(),
                    updated.site(),
                    POINT_CONTACT_UPDATE_SUCCESS.formatted(id)
            );
        } catch (Exception e) {
            throw new ProductException.UpdateException(OPERATION_ERROR.formatted("Update error", e.getMessage()));
        }
    }
}

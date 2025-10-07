package ru.romanov.marketplace.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.marketplace.productservice.utils.UUIDGenerator;
import ru.romanov.marketplace.productservice.exception.ProductException;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.PickupPointsPojo;
import ru.romanov.marketplace.productservice.mapper.PickupPointMapper;
import ru.romanov.marketplace.productservice.service.PickupPointService;
import ru.romanov.marketplace.productservice.persistence.view.PickupPointView;
import ru.romanov.marketplace.productservice.persistence.filter.PickupPointFilter;
import ru.romanov.marketplace.productservice.persistence.repository.PickupPointRepository;
import ru.romanov.marketplace.productservice.dto.response.PickupPointResponse;
import ru.romanov.marketplace.productservice.dto.request.PickupPointFindRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.PickupPointFindResponse;

import java.util.UUID;
import java.math.BigDecimal;

import static ru.romanov.marketplace.productservice.utils.MessageUtils.OPERATION_ERROR;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.PICKUP_POINT_NOT_FOUND;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.PICKUP_POINT_CREATE_SUCCESS;
import static ru.romanov.marketplace.productservice.utils.MessageUtils.PICKUP_POINT_UPDATE_SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class PickupPointServiceImpl implements PickupPointService {

    private final UUIDGenerator uuidGenerator;

    private final PickupPointMapper pickupPointMapper;

    private final PickupPointRepository pickupPointRepository;

    @Transactional
    @Override
    public PickupPointResponse create(PickupPointCreateRequest createRequest) {
        try {
            UUID id = uuidGenerator.generateUUID();

            PickupPointsPojo pickupPoint = pickupPointMapper.toCreatePojo(createRequest);
            pickupPoint.setId(id);
            pickupPoint.setRating(BigDecimal.ZERO);

            pickupPointRepository.create(pickupPoint, createRequest.employees());

            PickupPointFilter filter = new PickupPointFilter(id, null);
            PickupPointsPojo created = pickupPointRepository.findOne(filter, false);

            return pickupPointMapper.toResponse(created, PICKUP_POINT_CREATE_SUCCESS.formatted(id));
        } catch (Exception e) {
            throw new ProductException.CreationException(OPERATION_ERROR.formatted("Create error", e.getMessage()));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PickupPointFindResponse findOne(PickupPointFindRequest findRequest) {
        try {
            PickupPointFilter filter = pickupPointMapper.toFilter(findRequest);

            PickupPointsPojo pickupPoint = pickupPointRepository.findOne(filter, true);

            return pickupPointMapper.toFindResponse(pickupPoint, null);
        } catch (Exception e) {
            throw new ProductException.NotFoundException(OPERATION_ERROR.formatted("Found error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public PickupPointFindResponse findOneView(PickupPointFindRequest findRequest) {
        try {
            PickupPointFilter filter = pickupPointMapper.toFilter(findRequest);

            PickupPointView view = pickupPointRepository.findOneView(filter, true);

            return pickupPointMapper.toFindResponse(view.pickupPoint(), view.employees());
        } catch (Exception e) {
            throw new ProductException.NotFoundException(OPERATION_ERROR.formatted("Found error", e.getMessage()));
        }
    }

    @Transactional
    @Override
    public PickupPointResponse update(PickupPointUpdateRequest updateRequest) {
        try {
            UUID id = UUID.fromString(updateRequest.id());

            if (!pickupPointRepository.existsByIdWithLock(id)) {
                throw new ProductException.NotFoundException(PICKUP_POINT_NOT_FOUND.formatted(id));
            }

            PickupPointsPojo pickupPoint = pickupPointMapper.toUpdatePojo(updateRequest);
            pickupPointRepository.update(pickupPoint, updateRequest.employees());

            PickupPointFilter filter = new PickupPointFilter(id, null);
            PickupPointsPojo updated = pickupPointRepository.findOne(filter, false);

            return pickupPointMapper.toResponse(updated, PICKUP_POINT_UPDATE_SUCCESS.formatted(id));
        } catch (Exception e) {
            throw new ProductException.UpdateException(OPERATION_ERROR.formatted("Update error", e.getMessage()));
        }
    }
}

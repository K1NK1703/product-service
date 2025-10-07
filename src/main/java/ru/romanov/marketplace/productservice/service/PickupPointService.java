package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.dto.response.PickupPointResponse;
import ru.romanov.marketplace.productservice.dto.request.PickupPointFindRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.PickupPointFindResponse;

public interface PickupPointService {

    PickupPointResponse create(PickupPointCreateRequest createRequest);

    PickupPointFindResponse findOne(PickupPointFindRequest findRequest);

    PickupPointFindResponse findOneView(PickupPointFindRequest findRequest);

    PickupPointResponse update(PickupPointUpdateRequest updateRequest);

}

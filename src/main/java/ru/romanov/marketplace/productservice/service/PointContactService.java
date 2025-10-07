package ru.romanov.marketplace.productservice.service;

import ru.romanov.marketplace.productservice.dto.response.PointContactResponse;
import ru.romanov.marketplace.productservice.dto.request.PointContactCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.PointContactUpdateRequest;

public interface PointContactService {

    PointContactResponse create(PointContactCreateRequest createRequest);

    PointContactResponse findById(String id);

    PointContactResponse update(PointContactUpdateRequest updateRequest);

}

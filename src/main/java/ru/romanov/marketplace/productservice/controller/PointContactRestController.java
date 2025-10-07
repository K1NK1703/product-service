package ru.romanov.marketplace.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanov.marketplace.productservice.service.PointContactService;
import ru.romanov.marketplace.productservice.dto.response.PointContactResponse;
import ru.romanov.marketplace.productservice.dto.request.PointContactCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.PointContactUpdateRequest;

@RestController
@RequestMapping("/point-contacts")
@RequiredArgsConstructor
public class PointContactRestController {

    private final PointContactService pointContactService;

    @PostMapping("/create")
    public ResponseEntity<PointContactResponse> create(
            @RequestBody PointContactCreateRequest createRequest
    ) {
        PointContactResponse createResponse = pointContactService.create(createRequest);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PointContactResponse> findById(@PathVariable String id) {
        PointContactResponse findResponse = pointContactService.findById(id);
        return new ResponseEntity<>(findResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PointContactResponse> update(
            @RequestBody PointContactUpdateRequest updateRequest
    ) {
        PointContactResponse updateResponse = pointContactService.update(updateRequest);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}

package ru.romanov.marketplace.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanov.marketplace.productservice.service.PickupPointService;
import ru.romanov.marketplace.productservice.dto.response.PickupPointResponse;
import ru.romanov.marketplace.productservice.dto.request.PickupPointFindRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.PickupPointCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.PickupPointFindResponse;

@RestController
@RequestMapping("/pickup-points")
@RequiredArgsConstructor
public class PickupPointRestController {

    private final PickupPointService pickupPointService;

    @PostMapping("/create")
    public ResponseEntity<PickupPointResponse> create(
            @RequestBody PickupPointCreateRequest createRequest
    ) {
        PickupPointResponse createResponse = pickupPointService.create(createRequest);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<PickupPointFindResponse> findOne(
            @RequestBody PickupPointFindRequest findRequest
    ) {
        PickupPointFindResponse findResponse = pickupPointService.findOne(findRequest);
        return new ResponseEntity<>(findResponse, HttpStatus.OK);
    }

    @GetMapping("/find/view")
    public ResponseEntity<PickupPointFindResponse> findOneView(
            @RequestBody PickupPointFindRequest findRequest
    ) {
        PickupPointFindResponse findResponse = pickupPointService.findOneView(findRequest);
        return new ResponseEntity<>(findResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PickupPointResponse> update(
            @RequestBody PickupPointUpdateRequest updateRequest
    ) {
        PickupPointResponse updateResponse = pickupPointService.update(updateRequest);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}

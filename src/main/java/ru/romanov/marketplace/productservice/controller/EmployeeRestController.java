package ru.romanov.marketplace.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanov.marketplace.productservice.service.EmployeeService;
import ru.romanov.marketplace.productservice.jooq.tables.pojos.EmployeesPojo;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(
            @RequestBody EmployeeCreateRequest createRequest
    ) {
        EmployeeResponse createResponse = employeeService.create(createRequest);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeFindResponse> findById(@PathVariable String id) {
        EmployeeFindResponse findResponse = employeeService.findById(id);
        return new ResponseEntity<>(findResponse, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EmployeesPojo>> findByFilter(
            @RequestBody EmployeeFindRequest filterRequest
    ) {
        List<EmployeesPojo> filterResponse = employeeService.findByFilter(filterRequest);
        return new ResponseEntity<>(filterResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> update(
            @RequestBody EmployeeUpdateRequest updateRequest
    ) {
        EmployeeResponse updateResponse = employeeService.update(updateRequest);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
